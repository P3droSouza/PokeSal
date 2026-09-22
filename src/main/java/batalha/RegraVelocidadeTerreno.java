package batalha;
import model.Pokesal;
import terreno.EfeitoDoTerreno;
//Classe que aplica um dos requisitos autorais que é
public class RegraVelocidadeTerreno {
    private static final int ATAQUES_MINIMOS_PARA_PENALIDADE = 3;
    private static final int PENALIDADE_SPD = 10;

    public void aplicarPenalidade(Pokesal pokesal, EfeitoDoTerreno terreno) {
        boolean favorecido = pokesal.getElementos() == terreno.getElementoTerreno();
        boolean atingiuLimite = pokesal.getNumeroDeAtaques() >= ATAQUES_MINIMOS_PARA_PENALIDADE;
        if (favorecido && atingiuLimite) {
            pokesal.reduzirSpd(PENALIDADE_SPD);
        }
    }
}

