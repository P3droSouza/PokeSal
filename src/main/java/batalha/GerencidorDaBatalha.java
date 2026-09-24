package batalha;

import enums.Elementos;
import enums.StatusDoEfeito;
import java.util.Random;
import model.Ataque;
import model.Pokesal;
import model.Treinador;
import terreno.EfeitoDoTerreno;

/**
 * Faz a batalha entre dois treinadores funcionar : define a ordem de ataque, executa
 * ataques, aplica efeitos de fim de turno e diz quem é o vencedor.
 */
public class GerencidorDaBatalha {

  private static final double CHANCE_APLICAR_STATUS = 0.50;

  private final Treinador treinador1;
  private final Treinador treinador2;
  private final EfeitoDoTerreno terreno;

  private final DanoAtaque calculadoraDano;
  private final StatusPokesal gerenciadorStatus;
  private final RegraVelocidadeTerreno regraVelocidadeTerreno;
  private final Random random;

  /**
   * Cria o gerenciador de uma batalha entre dois treinadores em um terreno específico.
   */
  public GerencidorDaBatalha(Treinador treinador1, Treinador treinador2, EfeitoDoTerreno terreno) {
    this.treinador1 = treinador1;
    this.treinador2 = treinador2;
    this.terreno = terreno;
    this.calculadoraDano = new DanoAtaque();
    this.gerenciadorStatus = new StatusPokesal();
    this.regraVelocidadeTerreno = new RegraVelocidadeTerreno();
    this.random = new Random();
  }

  /**
   * Determina qual treinador ataca primeiro no turno atual, com base na velocidade
   * de seus Pokésais.
   */
  public Treinador recalculaAtacantePrioritario() {
    int velocidade1 = treinador1
        .getPokesal()
        .getVelocidade();
    int velocidade2 = treinador2
        .getPokesal()
        .getVelocidade();
    if (velocidade1 >= velocidade2) {
      return treinador1;
    }
    return treinador2;
  }

  /**
   * Retorna o treinador oponente de um dado treinador nesta batalha.
   */
  public Treinador getOponente(Treinador treinador) {
    if (treinador == treinador1) {
      return treinador2;
    }
    return treinador1;
  }

  /**
   * Executa um ataque de um treinador contra seu oponente, aplicando dano ou,
   * se o defensor ativou a proteção, anulando o ataque.
   */
  public void executarAtaque(Treinador atacante, Ataque ataque, boolean defensorUsaProtecao) {
    Treinador defensor = getOponente(atacante);
    Pokesal pokesalAtacante = atacante.getPokesal();
    Pokesal pokesalDefensor = defensor.getPokesal();

    pokesalAtacante.lancarAtaque(ataque);

    if (defensorUsaProtecao) {
      defensor.ativarProtecao();
      return;
    }

    int dano = calculadoraDano.calcularDano(ataque, pokesalAtacante, pokesalDefensor, terreno);
    pokesalDefensor.danoRecebido(dano);

    if (random.nextDouble() < CHANCE_APLICAR_STATUS) {
      pokesalDefensor.setStatusEfeito(statusPorElemento(pokesalAtacante.getElementos()));
    }
  }

  /**
   * Mapeia o elemento de um Pokésal atacante para o status que ele pode causar:
   * Fogo queima, Água paralisa, Planta envenena.
   */
  private StatusDoEfeito statusPorElemento(Elementos elemento) {
    if (elemento == Elementos.FOGO) {
      return StatusDoEfeito.QUEIMADO;
    } else if (elemento == Elementos.AGUA) {
      return StatusDoEfeito.PARALIZADO;
    } else {
      return StatusDoEfeito.ENVENENADO;
    }
  }

  /**
   * Aplica ao Pokésal os efeitos de fim de turno: status, efeito de fim de rodada
   * do terreno e a penalidade de velocidade do terreno.
   */
  private void aplicarEfeitosDeFimDeTurno(Pokesal pokesal) {
    gerenciadorStatus.aplicarEfeitoNoFimDaRodada(pokesal);
    terreno.aplicarEfeitoNoFimDaRodada(pokesal);
    regraVelocidadeTerreno.aplicarPenalidade(pokesal, terreno);
  }

  /**
   * Aplica, para os dois treinadores, os efeitos de fim de turno em seus Pokésais.
   */
  public void finalizarTurno() {
    aplicarEfeitosDeFimDeTurno(treinador1.getPokesal());
    aplicarEfeitosDeFimDeTurno(treinador2.getPokesal());
  }

  /**
   * Verifica se a batalha terminou.
   */
  public boolean batalhaTerminou() {
    return !treinador1
        .getPokesal()
        .pokesalestaVivo() || !treinador2
        .getPokesal()
        .pokesalestaVivo();
  }

  /**
   * Determina o vencedor da batalha.
   */
  public Treinador getVencedor() {
    if (!treinador1
        .getPokesal()
        .pokesalestaVivo()) {
      return treinador2;
    }
    if (!treinador2
        .getPokesal()
        .pokesalestaVivo()) {
      return treinador1;
    }
    return null;
  }
}