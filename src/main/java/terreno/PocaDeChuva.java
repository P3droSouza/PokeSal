package terreno;

import enums.Elementos;
import model.Ataque;
import model.Pokesal;

/**
 * Classe responsável por criar o terreno qu efavorece o elmento do tipo agua.
 */
public class PocaDeChuva implements EfeitoDoTerreno {
  private static final double BONUS_AGUA = 1.10;

  @Override
  public Elementos getElementoTerreno() {
    return Elementos.AGUA;
  }

  @Override
  public double modificarDano(Ataque ataque) {
    if (ataque.getElementos() == Elementos.AGUA) {
      return 1 + BONUS_AGUA;
    }
    return 0;
  }

  @Override
  public void aplicarEfeitoNoFimDaRodada(Pokesal pokesal) {

  }
}