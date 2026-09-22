import batalha.GerencidorDaBatalha;
import model.Ataque;
import model.Pokesal;
import model.Pokesais;
import model.Treinador;
import terreno.CanteiroCentral;
import terreno.EfeitoDoTerreno;

public class Main {

    public static void main(String[] args) {
        Pokesal charSal = Pokesais.Charsal();
        Pokesal squirtSal = Pokesais.SquirtSal();

        Treinador treinador1 = new Treinador("Ash", charSal);
        Treinador treinador2 = new Treinador("Pedro", squirtSal);

        EfeitoDoTerreno terreno = new CanteiroCentral();
        GerencidorDaBatalha battleManager = new GerencidorDaBatalha(treinador1, treinador2, terreno);

        int turno = 1;
        while (!battleManager.batalhaTerminou()) {
            System.out.println("--- Turno " + turno + " ---");

            Treinador atacante = battleManager.recalculaAtacantePrioritario();
            Ataque ataqueEscolhido = atacante.getPokesal().getAtaque_1();

            battleManager.executarAtaque(atacante, ataqueEscolhido, false);
            battleManager.finalizarTurno();

            System.out.println(treinador1.getNome() + " (" + charSal.getNome() + "): " + charSal.getVidaAtual() + " Vida");
            System.out.println(treinador2.getNome() + " (" + squirtSal.getNome() + "): " + squirtSal.getVidaAtual() + " Vida");

            turno++;
        }

        Treinador vencedor = battleManager.getVencedor();
        System.out.println(vencedor != null ? vencedor.getNome() + " venceu a batalha!" : "Empate.");
    }
}