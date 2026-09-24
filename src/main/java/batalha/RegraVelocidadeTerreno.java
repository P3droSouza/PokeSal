package batalha;

import model.Pokesal;
import terreno.EfeitoDoTerreno;

/**
 * Implementa o requisito autoral 2: a partir do 3º ataque, o Pokésal favorecido pelo
 * terreno tem sua velocidade reduzida, equilibrando a vantagem do terreno.
 */
public class RegraVelocidadeTerreno {
  private static final int ATAQUES_MINIMOS_PARA_VELOCIDADE_CAIR = 3;
  private static final int PENALIDADE_VELOCIDADE = 10;

  /**
   * Aplica a "penalidade" de velocidade ao Pokésal, se ele for do elemento favorecido
   * pelo terreno e já tiver atingido o número mínimo de ataques.
   */
  public void aplicarPenalidade(Pokesal pokesal, EfeitoDoTerreno terreno) {
    boolean favorecido = pokesal.getElementos() == terreno.getElementoTerreno();
    boolean atingiuLimite = pokesal.getNumeroDeAtaques() >= ATAQUES_MINIMOS_PARA_VELOCIDADE_CAIR;
    if (favorecido && atingiuLimite) {
      pokesal.reduzirSpd(PENALIDADE_VELOCIDADE);
    }
  }
}

