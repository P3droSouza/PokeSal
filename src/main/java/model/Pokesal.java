package model;
import enums.Elementos;
import enums.StatusDoEfeito;
import exception.AtaqueBloqueadoException;

public class Pokesal {
    public static final int VELOCIDADADE_MINIMA = 1;

    private final String nome;
    public final int defesa;
    public final int ataque;
    public final int vidaMaxima;
    public final Elementos elementos;
    public final Ataque ataque_1;
    public final Ataque ataque_2;
    private int vidaAtual;
    private int velocidade;
    private StatusDoEfeito statusDoEfeito;
    private Ataque ultimoAtaqueUsado;
    private int numeroDeAtaques;

    public Pokesal(String nome, int defesa, int ataque, int vidaMaxima, int velocidade, Elementos elementos, Ataque ataque1, Ataque ataque2) {
        this.nome = nome;
        this.defesa = defesa;
        this.ataque = ataque;
        this.vidaMaxima = vidaMaxima;
        this.elementos = elementos;
        this.ataque_1 = ataque1;
        this.ataque_2 = ataque2;
        this.vidaAtual = vidaMaxima;
        this.numeroDeAtaques = 0;
        this.ultimoAtaqueUsado = null;
        this.statusDoEfeito = StatusDoEfeito.SEM_EFEITO;
        this.velocidade = velocidade;
    }

    public void danoRecebido(int dano){
        this.vidaAtual = Math.max(0,this.vidaAtual - dano);
    }
    public void curaPokemon(int cura){
        this.vidaAtual = Math.min(vidaMaxima, this.vidaAtual + cura );
    }
    public boolean PokesalestaVivo(){
        return vidaAtual > 0;
    }
    //Verifica se o ataque informado está disponivel seguindo a regra de um dos requisitos autorais de não poder usar 2 vezes o mesmo ataque em seguida
    public boolean AtaqueDisponivel(Ataque ataque) {
        return ultimoAtaqueUsado == null || !ultimoAtaqueUsado.equals(ataque);
    }
    /**
        @throws AtaqueBloqueadoException
    */
    public void lancarAtaque(Ataque ataque){
        if(!AtaqueDisponivel(ataque)){
            throw new AtaqueBloqueadoException("O ataque" + ataque.getNome() + "não pode ser usado nesse turno");
            }
            this.ultimoAtaqueUsado = ataque;
            this.numeroDeAtaques++;
        }
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

    public Ataque getAtaque_1() {
        return ataque_1;
    }

    public Ataque getAtaque_2() {
        return ataque_2;
    }

    public Ataque getUltimoAtaqueUsado() {
        return ultimoAtaqueUsado;
    }

    public int getNumeroDeAtaques() {
        return numeroDeAtaques;
    }
}



