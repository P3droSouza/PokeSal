package model;

import enums.Elementos;
import enums.StatusDoEfeito;

/**
 * Representa um Pokésal em batalha: seus atributos, ataques disponíveis e estado atual
 * (vida, velocidade, status e histórico de ataques usados).
 */
public class Pokesal {
  public static final int VELOCIDADADE_MINIMA = 1;
  public final int defesa;
  public final int ataque;
  public final int vidaMaxima;
  public final Elementos elementos;
  public final Ataque atk1;
  public final Ataque atk2;
  private final String nome;
  private int vidaAtual;
  private int velocidade;
  private StatusDoEfeito statusDoEfeito;
  private Ataque ultimoAtaqueUsado;
  private int numeroDeAtaques;

  /**
   * Cria um Pokésal com seus atributos base e os dois ataques disponíveis.
   */
  public Pokesal(String nome, int defesa, int ataque, int vidaMaxima, int velocidade,
                 Elementos elementos, Ataque ataque1, Ataque ataque2) {
    this.nome = nome;
    this.defesa = defesa;
    this.ataque = ataque;
    this.vidaMaxima = vidaMaxima;
    this.elementos = elementos;
    this.atk1 = ataque1;
    this.atk2 = ataque2;
    this.vidaAtual = vidaMaxima;
    this.numeroDeAtaques = 0;
    this.ultimoAtaqueUsado = null;
    this.statusDoEfeito = StatusDoEfeito.SEM_EFEITO;
    this.velocidade = velocidade;
  }

  /**
   * Reduz a vida atual do Pokésal, sem deixá-la ficar negativa.
   */
  public void danoRecebido(int dano) {
    this.vidaAtual = Math.max(0, this.vidaAtual - dano);
  }

  /**
   * Restaura vida do Pokésal, sem ultrapassar a vida máxima.
   */
  public void curaPokemon(int cura) {
    this.vidaAtual = Math.min(vidaMaxima, this.vidaAtual + cura);
  }

  /**
   * Indica se o Pokésal ainda está vivo.
   */
  public boolean pokesalestaVivo() {
    return vidaAtual > 0;
  }

  /**
   * Verifica se o ataque informado está disponivel seguindo a regra de um dos requisitos autorais.
   * de não poder usar 2 vezes o mesmo ataque em seguida
   */
  public boolean ataqueDisponivel(Ataque ataque) {
    return ultimoAtaqueUsado == null || !ultimoAtaqueUsado.equals(ataque);
  }

  /**
   * Registra o ataque como o último usado e incrementa o contador de ataques do Pokésal.
   */
  public void lancarAtaque(Ataque ataque) {
    this.ultimoAtaqueUsado = ataque;
    this.numeroDeAtaques++;
  }

  /**
   * Reduz a velocidade do Pokésal.
   * sem deixar a velocidade abaixo do minimoque é 1
   */
  public void reduzirSpd(int quantidade) {
    this.velocidade = Math.max(VELOCIDADADE_MINIMA, this.velocidade - quantidade);
  }

  public String getNome() {
    return nome;
  }

  public int getVidaAtual() {
    return vidaAtual;
  }

  public int getVidaMaxima() {
    return vidaMaxima;
  }

  public int getAtaque() {
    return ataque;
  }

  public int getDefesa() {
    return defesa;
  }

  public int getVelocidade() {
    return velocidade;
  }

  public Elementos getElementos() {
    return elementos;
  }

  public StatusDoEfeito getStatusDoEfeito() {
    return statusDoEfeito;
  }

  public void setStatusEfeito(StatusDoEfeito statusDoEfeito) {
    this.statusDoEfeito = statusDoEfeito;
  }

  public Ataque getAtk1() {
    return atk1;
  }

  public Ataque getAtk2() {
    return atk2;
  }

  public Ataque getUltimoAtaqueUsado() {
    return ultimoAtaqueUsado;
  }

  public int getNumeroDeAtaques() {
    return numeroDeAtaques;
  }
}