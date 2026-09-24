package items;

import model.Pokesal;

/**
 * Item que restaura uma quantidade menor da vida do Pokésal.
 */
public class Potion implements Item {
  private static final int CURA_POTION = 40;

  @Override
  public void aplicarEfeito(Pokesal pokesal) {
    pokesal.curaPokemon(CURA_POTION);
  }
}
