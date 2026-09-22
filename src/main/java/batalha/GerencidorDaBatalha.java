package batalha;
import model.Ataque;
import model.Pokesal;
import model.Treinador;
import terreno.EfeitoDoTerreno;

public class GerencidorDaBatalha {

    private final Treinador treinador1;
    private final Treinador treinador2;
    private final EfeitoDoTerreno terreno;

    private final DanoAtaque calculadoraDano;
    private final StatusPokesal gerenciadorStatus;
    private final RegraVelocidadeTerreno regraVelocidadeTerreno;

    public GerencidorDaBatalha(Treinador treinador1, Treinador treinador2, EfeitoDoTerreno terreno) {
        this.treinador1 = treinador1;
        this.treinador2 = treinador2;
        this.terreno = terreno;
        this.calculadoraDano = new DanoAtaque();
        this.gerenciadorStatus = new StatusPokesal();
        this.regraVelocidadeTerreno = new RegraVelocidadeTerreno();
    }

    public Treinador recalculaAtacantePrioritario() {
        return treinador1.getPokesal().getVelocidade() >= treinador2.getPokesal().getVelocidade() ? treinador1 : treinador2;
    }

    public Treinador getOponente(Treinador treinador) {
        return treinador == treinador1 ? treinador2 : treinador1;
    }

    //se o ataque repetir o último usado
    /**
        @throws exception.AtaqueBloqueadoException
     */

    public void executarAtaque(Treinador atacante, Ataque ataque, boolean defensorUsaProtecao) {
        Treinador defensor = getOponente(atacante);
        Pokesal pokesalAtacante = atacante.getPokesal();
        Pokesal pokesalDefensor = defensor.getPokesal();

        pokesalAtacante.AtaqueDisponivel(ataque);

        if (defensorUsaProtecao) {
            defensor.ativarProtecao();
            return;
        }

        int dano = calculadoraDano.calcularDano(ataque, pokesalAtacante, pokesalDefensor, terreno);
        pokesalDefensor.danoRecebido(dano);
    }

    public void finalizarTurno() {
        for (Treinador treinador : new Treinador[] {treinador1, treinador2}) {
            Pokesal pokesal = treinador.getPokesal();
            gerenciadorStatus.aplicarEfeitoNoFimDaRodada(pokesal);
            terreno.aplicarEfeitoNoFimDaRodada(pokesal);
            regraVelocidadeTerreno.aplicarPenalidade(pokesal, terreno);
        }
    }

    public boolean batalhaTerminou() {
        return !treinador1.getPokesal().PokesalestaVivo() || !treinador2.getPokesal().PokesalestaVivo();
    }

    public Treinador getVencedor() {
        if (!treinador1.getPokesal().PokesalestaVivo()) {
            return treinador2;
        }
        if (!treinador2.getPokesal().PokesalestaVivo()) {
            return treinador1;
        }
        return null;
    }
}
