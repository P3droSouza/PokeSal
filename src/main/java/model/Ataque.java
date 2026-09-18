package model;
import enums.Elementos;
public class Ataque {
    private final String nome; //nome do ataque do ataque do pokemon
    private final Elementos elementos;
    private final int ataqueBase;//diferentes danos

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


