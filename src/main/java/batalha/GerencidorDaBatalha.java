package batalha;

import model.Ataque;
import model.Pokesal;
import model.Treinador;
import terreno.EfeitoDoTerreno;

/**
 * Faz a batalha entre dois treinadores funcionar : define a ordem de ataque, executa
 * ataques, aplica efeitos de fim de turno e diz quem é o vencedor.
 */
public class GerencidorDaBatalha {

  private final Treinador treinador1;
  private final Treinador treinador2;
  private final EfeitoDoTerreno terreno;

  private final DanoAtaque calculadoraDano;
  private final StatusPokesal gerenciadorStatus;
  private final RegraVelocidadeTerreno regraVelocidadeTerreno;

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
  }

  /**
   * Determina qual treinador ataca primeiro no turno atual, com base na velocidade
   * de seus Pokésais.
   */
  public Treinador recalculaAtacantePrioritario() {
    return treinador1
        .getPokesal()
        .getVelocidade() >= treinador2
        .getPokesal()
        .getVelocidade() ? treinador1 : treinador2;
  }

  /**
   * Retorna o treinador oponente de um dado treinador nesta batalha.
   */
  public Treinador getOponente(Treinador treinador) {
    return treinador == treinador1 ? treinador2 : treinador1;
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
  }

  /**
   * Aplica, para os dois treinadores, os efeitos de fim de turno: status do Pokésal,
   * efeito de fim de rodada do terreno e a penalidade de velocidade do terreno.
   */
  public void finalizarTurno() {
    for (Treinador treinador : new Treinador[] {treinador1, treinador2}) {
      Pokesal pokesal = treinador.getPokesal();
      gerenciadorStatus.aplicarEfeitoNoFimDaRodada(pokesal);
      terreno.aplicarEfeitoNoFimDaRodada(pokesal);
      regraVelocidadeTerreno.aplicarPenalidade(pokesal, terreno);
    }
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