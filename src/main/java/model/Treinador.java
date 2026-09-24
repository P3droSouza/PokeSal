package model;

import items.Item;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um treinador em batalha: seu Pokésal, os itens que pode usar e o
 * controle da proteção do requisito autoral 3.
 */
public class Treinador {

  private static final int ITENS_POR_BATALHA = 2;

  private final String nome;
  private final Pokesal pokesal;
  private final List<Item> mochila;
  private int itensUsados;
  private boolean protecaoDisponivel;

  /**
   * Cria um treinador com seu Pokésal, mochila vazia e proteção disponível.
   */
  public Treinador(String nome, Pokesal pokesal) {
    this.nome = nome;
    this.pokesal = pokesal;
    this.mochila = new ArrayList<>();
    this.itensUsados = 0;
    this.protecaoDisponivel = true;
  }

  public String getNome() {
    return nome;
  }

  public Pokesal getPokesal() {
    return pokesal;
  }

  public List<Item> getMochila() {
    return mochila;
  }

  /**
   * Informa se o treinador ainda pode usar itens nesta batalha.
   */
  public boolean podeUsarItem() {
    return itensUsados < ITENS_POR_BATALHA;
  }

  /**
   * Usa um item no Pokésal do treinador, aplicando seu efeito e consumindo uma das
   * duas chances de uso de item por batalha.
   */
  public void usarItem(Item item) {
    if (!podeUsarItem()) {
      throw new IllegalStateException(
          "Limite de " + ITENS_POR_BATALHA + " itens por batalha já foi atingido.");
    }
    item.aplicarEfeito(pokesal);
    itensUsados++;
  }

  /**
   * Ativa a proteção do requisito autoral 3, anulando o próximo ataque sofrido pelo Pokésal.
   *
   * @throws IllegalStateException se a proteção já foi usada nesta batalha.
   */
  public void ativarProtecao() {
    if (!protecaoDisponivel) {
      throw new IllegalStateException("A proteção já foi usada na batalha .");
    }
    protecaoDisponivel = false;
  }

  /**
   * Indica se o treinador ainda pode ativar a proteção nesta batalha.
   *
   * @return se a proteção ainda não foi usada.
   */
  public boolean estaProtecaoDisponivel() {
    return protecaoDisponivel;
  }
}