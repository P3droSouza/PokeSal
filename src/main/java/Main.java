import batalha.GerencidorDaBatalha;
import java.util.Scanner;
import model.Ataque;
import model.Pokesais;
import model.Pokesal;
import model.Treinador;
import terreno.CanteiroCentral;
import terreno.EfeitoDoTerreno;

/**
 * Classe principal responsável por executar a simulação de batalha do PokeSal via console.
 */
public class Main {

    /**
     * Ponto de inicio: executa a batalha entre dois treinadores
     *  até ter um vencedor.
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

            Ataque ataqueAtacante = escolherAtaque(scanner, atacante);
            gerencidorDaBatalha.executarAtaque(atacante, ataqueAtacante, false);

            if (defensor.getPokesal().PokesalestaVivo()) {
                Ataque ataqueDefensor = escolherAtaque(scanner, defensor);
                gerencidorDaBatalha.executarAtaque(defensor, ataqueDefensor, false);
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