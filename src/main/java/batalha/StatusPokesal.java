package batalha;

import model.Pokesal;

/**
 * Aplica ao fim de cada rodada, o efeito correspondente ao status atual do Pokésal.
 */
public class StatusPokesal {
  private static final double DANO_PERCENTUAL_QUEIMADO = 0.05;
  private static final double DANO_PERCENTUAL_ENVENENADO = 0.08;
  private static final int REDUCAO_SPD_PARALISADO = 5;

  /**
   * Aplica o efeito de fim de rodada de acordo com o status atual do Pokésal.
   */
  public void aplicarEfeitoNoFimDaRodada(Pokesal pokesal) {
    switch (pokesal.getStatusDoEfeito()) {
      case QUEIMADO ->
          pokesal.danoRecebido((int) (pokesal.getVidaMaxima() * DANO_PERCENTUAL_QUEIMADO));
      case ENVENENADO ->
          pokesal.danoRecebido((int) (pokesal.getVidaMaxima() * DANO_PERCENTUAL_ENVENENADO));
      case PARALIZADO -> pokesal.reduzirSpd(REDUCAO_SPD_PARALISADO);
      default -> {
      }
    }
  }
}

