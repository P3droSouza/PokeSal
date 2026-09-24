package terreno;

import enums.Elementos;
import model.Ataque;
import model.Pokesal;

/**
 * Classe responsável por criar o terreno qu efavorece o elmento do tipo planta.
 */
public class CanteiroCentral implements EfeitoDoTerreno {

  private static final double REGEN_PLANTA = 0.05;

  @Override
  public Elementos getElementoTerreno() {
    return Elementos.PLANTA;
  }

  @Override
  public double modificarDano(Ataque ataque) {
    return 1.0;
  }

  @Override
  public void aplicarEfeitoNoFimDaRodada(Pokesal pokesal) {
    if (pokesal.getElementos() == Elementos.PLANTA) {
      int cura = (int) (pokesal.getVidaMaxima() * REGEN_PLANTA);
      pokesal.curaPokemon(cura);
    }
  }
}