package terreno;

import enums.Elementos;
import model.Ataque;
import model.Pokesal;

/**
 * Classe responsável por criar o terreno qu efavorece o elmento do tipo agua.
 */
public class AsfaltoQuente implements EfeitoDoTerreno {
  private static final double BONUS_FOGO = 1.50;

  @Override
  public Elementos getElementoTerreno() {
    return Elementos.FOGO;
  }

  @Override
  public double modificarDano(Ataque ataque) {
    if (ataque.getElementos() == Elementos.FOGO) {
      return 1 + BONUS_FOGO;
    }
    return 0;
  }

  @Override
  public void aplicarEfeitoNoFimDaRodada(Pokesal pokesal) {
  }
}
