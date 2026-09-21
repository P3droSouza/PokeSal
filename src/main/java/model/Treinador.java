package model;

import java.util.ArrayList;
import java.util.List;
import items.Item;

public class Treinador {

    private static final int ITENS_POR_BATALHA = 2;

    private final String nome;
    private final Pokesal pokesal;
    private final List<Item> mochila;

    public String getNome() {
        return nome;
    }

    public Pokesal getPokesal() {
        return pokesal;
    }

    public List<Item> getMochila() {
        return mochila;
    }

    private int itensUsados;

    private boolean protecaoDisponivel;

    public Treinador(String nome, Pokesal pokesal) {
        this.nome = nome;
        this.pokesal = pokesal;
        this.mochila = new ArrayList<>();
        this.itensUsados = 0;
        this.protecaoDisponivel = true;
    }

    public boolean podeUsarItem() {
        return itensUsados < ITENS_POR_BATALHA;
    }

    public void usarItem(Item item) {
        if (!podeUsarItem()) {
            throw new IllegalStateException("Limite de " + ITENS_POR_BATALHA + " itens por batalha já foi atingido.");
        }
        item.aplicarEfeito(pokesal);
        itensUsados++;
    }

    public void ativarProtecao() {
        if (!protecaoDisponivel) {
            throw new IllegalStateException("A proteção já foi usada na batalha .");
        }
        protecaoDisponivel = false;
    }

    public boolean EstaProtecaoDisponivel() {
        return protecaoDisponivel;
    }
}