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

  private static final int CHIKOSAL_VIDA = 300;
  private static final int CHIKOSAL_ATAQUE = 49;
  private static final int CHIKOSAL_DEFESA = 65;
  private static final int CHIKOSAL_VELOCIDADE = 45;
  private static final int CHIKOSAL_ATAQUE_FRACO = 35;
  private static final int CHIKOSAL_ATAQUE_FORTE = 55;

  private static final int CYNDASAL_VIDA = 300;
  private static final int CYNDASAL_ATAQUE = 52;
  private static final int CYNDASAL_DEFESA = 43;
  private static final int CYNDASAL_VELOCIDADE = 65;
  private static final int CYNDASAL_ATAQUE_FRACO = 40;
  private static final int CYNDASAL_ATAQUE_FORTE = 60;

  private static final int TOTOSAL_VIDA = 300;
  private static final int TOTOSAL_ATAQUE = 48;
  private static final int TOTOSAL_DEFESA = 65;
  private static final int TOTOSAL_VELOCIDADE = 43;
  private static final int TOTOSAL_ATAQUE_FRACO = 35;
  private static final int TOTOSAL_ATAQUE_FORTE = 55;

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

  /**
   * Cria o Pokésal inicial do tipo Planta.
   */
  public static Pokesal chikoSal() {
    Ataque fraco = new Ataque("Folha Navalha", Elementos.PLANTA, CHIKOSAL_ATAQUE_FRACO);
    Ataque forte = new Ataque("Raio Solar", Elementos.PLANTA, CHIKOSAL_ATAQUE_FORTE);
    return new Pokesal("ChikoSal", CHIKOSAL_DEFESA, CHIKOSAL_ATAQUE, CHIKOSAL_VIDA,
        CHIKOSAL_VELOCIDADE, Elementos.PLANTA, fraco, forte);
  }

  /**
   * Cria o Pokésal inicial do tipo Fogo.
   */
  public static Pokesal cyndaSal() {
    Ataque fraco = new Ataque("Investida em Chamas", Elementos.FOGO, CYNDASAL_ATAQUE_FRACO);
    Ataque forte = new Ataque("Chamas Fúteis", Elementos.FOGO, CYNDASAL_ATAQUE_FORTE);
    return new Pokesal("CyndaSal", CYNDASAL_DEFESA, CYNDASAL_ATAQUE, CYNDASAL_VIDA,
        CYNDASAL_VELOCIDADE, Elementos.FOGO, fraco, forte);
  }

  /**
   * Cria o Pokésal inicial do tipo Agua.
   */
  public static Pokesal totoSal() {
    Ataque fraco = new Ataque("Mordida", Elementos.AGUA, TOTOSAL_ATAQUE_FRACO);
    Ataque forte = new Ataque("Surf", Elementos.AGUA, TOTOSAL_ATAQUE_FORTE);
    return new Pokesal("TotoSal", TOTOSAL_DEFESA, TOTOSAL_ATAQUE, TOTOSAL_VIDA,
        TOTOSAL_VELOCIDADE, Elementos.AGUA, fraco, forte);
  }
}