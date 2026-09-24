package model;

import enums.Elementos;

/**
 * Representa um ataque que um Pokésal pode usar: nome, elemento e poder base de dano.
 */
public class Ataque {
  private final String nome; // nome do ataque do ataque do pokemon
  private final Elementos elementos;
  private final int ataqueBase; // diferentes danos

  /**
   * Cria um novo ataque.
   */
  public Ataque(String nome, Elementos elementos, int ataquebase) {
    this.nome = nome;
    this.elementos = elementos;
    this.ataqueBase = ataquebase;
  }

  public String getNome() {
    return nome;
  }

  public Elementos getElementos() {
    return elementos;
  }

  public int getAtaqueBase() {
    return ataqueBase;
  }
}


