package model;

import enums.Elementos;

/**
 * Classe responsável de criar os Pokésais iniciais do jogo, já com
 * seus atributos e ataques pré-definidos.
 */
public class Pokesais {
  private static final int BULBASAL_VIDA = 300;
  private static final int BULBASAL_ATAQUE = 49;
  private static final int BULBASAL_DEFEFSA = 49;
  private static final int BULBASAL_VELOCIDADE = 45;
  private static final int BULBASAL_ATAQUE_FRACO = 35;
  private static final int BULBASAL_ATAQUE_FORTE = 55;

  private static final int CHARSAL_VIDA = 300;
  private static final int CHARSAL_ATAQUE = 52;
  private static final int CHARSAL_DEFESA = 43;
  private static final int CHARSAL_VELOCIDADE = 65;
  private static final int CHARSAL_ATAQUE_FRACO = 40;
  private static final int CHARSAL_ATAQUE_FORTE = 60;

  private static final int SQUIRTSAL_VIDA = 300;
  private static final int SQUIRTSAL_ATAQUE = 48;
  private static final int SQUIRTSAL_DEFESA = 65;
  private static final int SQUIRTSAL_VELOCIDADE = 43;
  private static final int SQUIRTSAL_ATAQUE_FRACO = 35;
  private static final int SQUIRTSAL_ATAQUE_FORTE = 55;

  private Pokesais() {
  }

  /**
   * Cria o Pokésal inicial do tipo Planta.
   */
  public static Pokesal bubasal() {
    Ataque fraco = new Ataque("Chicote de Cipó", Elementos.PLANTA, BULBASAL_ATAQUE_FRACO);
    Ataque forte = new Ataque("Folha Navalha", Elementos.PLANTA, BULBASAL_ATAQUE_FORTE);
    return new Pokesal("Bubasal", BULBASAL_DEFEFSA, BULBASAL_ATAQUE, BULBASAL_VIDA,
        BULBASAL_VELOCIDADE, Elementos.PLANTA, fraco, forte);
  }

  /**
   * Cria o Pokésal inicial do tipo Fogo.
   */
  public static Pokesal charsal() {
    Ataque fraco = new Ataque("Brasa", Elementos.FOGO, CHARSAL_ATAQUE_FRACO);
    Ataque forte = new Ataque("Lança Chamas", Elementos.FOGO, CHARSAL_ATAQUE_FORTE);
    return new Pokesal("Charsal", CHARSAL_DEFESA, CHARSAL_ATAQUE, CHARSAL_VIDA, CHARSAL_VELOCIDADE,
        Elementos.FOGO, fraco, forte);
  }

  /**
   * Cria o Pokésal inicial do tipo Agua.
   */
  public static Pokesal squirtSal() {
    Ataque fraco = new Ataque("Jato d'Água", Elementos.AGUA, SQUIRTSAL_ATAQUE_FRACO);
    Ataque forte = new Ataque("Hidrobomba", Elementos.AGUA, SQUIRTSAL_ATAQUE_FORTE);
    return new Pokesal("SquirtSal", SQUIRTSAL_DEFESA, SQUIRTSAL_ATAQUE, SQUIRTSAL_VIDA,
        SQUIRTSAL_VELOCIDADE, Elementos.AGUA, fraco, forte);
  }
}
