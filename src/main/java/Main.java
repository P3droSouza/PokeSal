import batalha.GerencidorDaBatalha;
import items.Antidote;
import items.Item;
import items.Potion;
import items.SuperPotion;
import model.Ataque;
import model.Pokesais;
import model.Pokesal;
import model.Treinador;
import terreno.CanteiroCentral;
import terreno.EfeitoDoTerreno;

import java.util.Scanner;


/**
 * Classe principal responsável por executar a simulação de batalha do PokeSal via console.
 */
public class Main {

    /**
     * Ponto de inicio: executa a batalha entre dois treinadores
     * até ter um vencedor.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Pokesal charSal = Pokesais.Charsal();
        Pokesal squirtSal = Pokesais.SquirtSal();

        Treinador treinador1 = new Treinador("Ash", charSal);
        Treinador treinador2 = new Treinador("Brook", squirtSal);

        EfeitoDoTerreno terreno = new CanteiroCentral();
        GerencidorDaBatalha gerencidorDaBatalha =
                new GerencidorDaBatalha(treinador1, treinador2, terreno);

        int turno = 1;
        while (!gerencidorDaBatalha.batalhaTerminou()) {
            System.out.println("--- Turno " + turno + " ---");

            Treinador atacante = gerencidorDaBatalha.recalculaAtacantePrioritario();
            Treinador defensor = gerencidorDaBatalha.getOponente(atacante);

            executarTurnoDoTreinador(scanner, gerencidorDaBatalha, atacante, defensor);

            if (defensor.getPokesal().PokesalestaVivo()) {
                executarTurnoDoTreinador(scanner, gerencidorDaBatalha, defensor, atacante);
            }

            gerencidorDaBatalha.finalizarTurno();

            System.out.println(treinador1.getNome() + " (" + charSal.getNome() + "): "
                    + charSal.getVidaAtual() + " Vida");
            System.out.println(treinador2.getNome() + " (" + squirtSal.getNome() + "): "
                    + squirtSal.getVidaAtual() + " Vida");

            turno++;
        }

        Treinador vencedor = gerencidorDaBatalha.getVencedor();
        System.out.println(vencedor
                != null ? vencedor.getNome() + " venceu a batalha!" : "Empate.");
        scanner.close();
    }

    private static void executarTurnoDoTreinador(Scanner scanner,
                                                 GerencidorDaBatalha gerencidorDaBatalha, Treinador quemAge, Treinador oponente) {
        if (quemAge.podeUsarItem() && perguntarSimNao(scanner,
                quemAge.getNome() + ", usar um item em vez de atacar? (s/n): ")) {
            usarItem(scanner, quemAge);
            return;
        }

        Ataque ataqueEscolhido = escolherAtaque(scanner, quemAge);

        boolean oponenteUsaProtecao = oponente.EstaProtecaoDisponivel()
                && perguntarSimNao(scanner, oponente.getNome() + ", usar proteção para anular esse ataque? (s/n): ");

        gerencidorDaBatalha.executarAtaque(quemAge, ataqueEscolhido, oponenteUsaProtecao);
    }

    private static boolean perguntarSimNao(Scanner scanner, String pergunta) {
        System.out.print(pergunta);
        return scanner.next().equalsIgnoreCase("s");
    }

    private static void usarItem(Scanner scanner, Treinador treinador) {
        System.out.println("1 - Potion (cura 40)");
        System.out.println("2 - Super Potion (cura 80)");
        System.out.println("3 - Antidote (remove status)");
        System.out.print("Escolha o item: ");

        Item item = switch (scanner.nextInt()) {
            case 2 -> new SuperPotion();
            case 3 -> new Antidote();
            default -> new Potion();
        };
        treinador.usarItem(item);
    }

    private static Ataque escolherAtaque(Scanner scanner, Treinador treinador) {
        Pokesal pokesal = treinador.getPokesal();
        Ataque ataque1 = pokesal.getAtaque_1();
        Ataque ataque2 = pokesal.getAtaque_2();

        System.out.println(treinador.getNome() + " (" + pokesal.getNome() + "), escolha o ataque:");
        System.out.println("1 - " + ataque1.getNome()
                + (pokesal.AtaqueDisponivel(ataque1) ? "" : " (bloqueado, repetido)"));
        System.out.println("2 - " + ataque2.getNome()
                + (pokesal.AtaqueDisponivel(ataque2) ? "" : " (bloqueado, repetido)"));

        Ataque escolhido = null;
        while (escolhido == null) {
            System.out.print("Digite 1 ou 2: ");
            int escolha = scanner.nextInt();
            Ataque candidato = escolha == 1 ? ataque1 : escolha == 2 ? ataque2 : null;

            if (candidato == null) {
                System.out.println("Opção inválida.");
            } else if (!pokesal.AtaqueDisponivel(candidato)) {
                System.out.println("Esse ataque foi usado no turno anterior, escolha o outro.");
            } else {
                escolhido = candidato;
            }
        }
        return escolhido;
    }
}