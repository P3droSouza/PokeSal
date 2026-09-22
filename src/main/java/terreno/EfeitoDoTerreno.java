package terreno;
import enums.Elementos;
import model.Ataque;
import model.Pokesal;

public interface EfeitoDoTerreno {
    Elementos getElementoTerreno();
    double modificarDano (Ataque ataque);
    void aplicarEfeitoNoFimDaRodada(Pokesal pokesal);

}
