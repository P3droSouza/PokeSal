package items;

import enums.StatusDoEfeito;
import model.Pokesal;

/**
 * Item que remove qualquer status alterado do Pokésal.
 */
public class Antidote implements Item {
  @Override
  public void aplicarEfeito(Pokesal pokesal) {
    pokesal.setStatusEfeito(StatusDoEfeito.SEM_EFEITO);
  }
}
