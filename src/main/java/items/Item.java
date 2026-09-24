package items;

import model.Pokesal;

/**
 * Interface criada para qualquer item que um treinador queira.
 * usar em um Pokésal durante a batalha.
 */
public interface Item {

  /**
   * Aplica o efeito do item no Pokésal informado.
   */
  void aplicarEfeito(Pokesal pokesal);
}
