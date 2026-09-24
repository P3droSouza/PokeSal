package terreno;

import enums.Elementos;
import model.Ataque;
import model.Pokesal;

/**
 * Interface que cria um "contrato" para os efeitos de terreno na batalha.
 */
public interface EfeitoDoTerreno {
  /**
   * Retorna o elemento favorecido por este terreno.
   */
  Elementos getElementoTerreno();

  /**
   * Calcula o multiplicador de dano aplicado a um ataque, de acordo com este terreno.
   */
  double modificarDano(Ataque ataque);

  /**
   * Aplica o efeito de fim de rodada deste terreno em um Pokésal.
   */
  void aplicarEfeitoNoFimDaRodada(Pokesal pokesal);
}
