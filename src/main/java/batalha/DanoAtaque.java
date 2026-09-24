package batalha;

import enums.Elementos;
import model.Ataque;
import model.Pokesal;
import terreno.EfeitoDoTerreno;

/**
 * Responsável por calcular o dano de um ataque, considerando vantagem de tipo,
 * atributos dos Pokésais envolvidos e o modificador do terreno.
 */
public class DanoAtaque {
  private static final double MULTIPLICADOR_SUPER_EFETIVO = 2.0;
  private static final double MULTIPLICADOR_POUCO_EFETIVO = 0.5;
  private static final double MULTIPLICADOR_NEUTRO = 1.0;

  /**
   * Calcula o multiplicador de dano decorrente da vantagem de tipo entre atacante e defensor
   * (Fogo > Planta > Água > Fogo).
   */
  public double calcularMultiplicadorTipo(Elementos atacante, Elementos defensor) {
    if (atacante == Elementos.FOGO && defensor == Elementos.PLANTA) {
      return MULTIPLICADOR_SUPER_EFETIVO;
    }
    if (atacante == Elementos.FOGO && defensor == Elementos.AGUA) {
      return MULTIPLICADOR_POUCO_EFETIVO;
    }
    if (atacante == Elementos.AGUA && defensor == Elementos.FOGO) {
      return MULTIPLICADOR_SUPER_EFETIVO;
    }
    if (atacante == Elementos.AGUA && defensor == Elementos.PLANTA) {
      return MULTIPLICADOR_POUCO_EFETIVO;
    }
    if (atacante == Elementos.PLANTA && defensor == Elementos.AGUA) {
      return MULTIPLICADOR_SUPER_EFETIVO;
    }
    if (atacante == Elementos.PLANTA && defensor == Elementos.FOGO) {
      return MULTIPLICADOR_POUCO_EFETIVO;
    }
    return MULTIPLICADOR_NEUTRO;
  }

  /**
   * Calcula o dano final de um ataque, combinando poder base, atributos dos Pokésais,
   * vantagem de tipo e o modificador do terreno.
   */
  public int calcularDano(Ataque ataque, Pokesal atacante, Pokesal defensor,
                          EfeitoDoTerreno terreno) {
    double multiplicadorTipo =
        calcularMultiplicadorTipo(ataque.getElementos(), defensor.getElementos());
    double modificadorTerreno = terreno.modificarDano(ataque);
    double danoBase = (double) ataque.getAtaqueBase() * atacante.getAtaque() / defensor.getDefesa();
    return (int) Math.round(danoBase * multiplicadorTipo * modificadorTerreno);
  }
}


