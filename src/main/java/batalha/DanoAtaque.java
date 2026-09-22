package batalha;
import enums.Elementos;
import model.Ataque;
import model.Pokesal;
import terreno.EfeitoDoTerreno;

public class DanoAtaque {
    private static final double MULTIPLICADOR_SUPER_EFETIVO = 2.0;
    private static final double MULTIPLICADOR_POUCO_EFETIVO = 0.5;
    private static final double MULTIPLICADOR_NEUTRO = 1.0;

    public double calcularMultiplicadorTipo(Elementos atacante, Elementos defensor) {
        if (atacante == Elementos.FOGO && defensor == Elementos.PLANTA) {
            return MULTIPLICADOR_SUPER_EFETIVO;
        }
        if (atacante == Elementos.FOGO && defensor == Elementos.AGUA) {
            return MULTIPLICADOR_POUCO_EFETIVO;
        }
        if (atacante == Elementos.AGUA && defensor == Elementos.FOGO) {
            return MULTIPLICADOR_SUPER_EFETIVO;
        }
        if (atacante == Elementos.AGUA && defensor == Elementos.PLANTA) {
            return MULTIPLICADOR_POUCO_EFETIVO;
        }
        if (atacante == Elementos.PLANTA && defensor == Elementos.AGUA) {
            return MULTIPLICADOR_SUPER_EFETIVO;
        }
        if (atacante == Elementos.PLANTA && defensor == Elementos.FOGO) {
            return MULTIPLICADOR_POUCO_EFETIVO;
        }
        return MULTIPLICADOR_NEUTRO;
    }

    public int calcularDano(Ataque ataque, Pokesal atacante, Pokesal defensor, EfeitoDoTerreno terreno) {
        double multiplicadorTipo = calcularMultiplicadorTipo(ataque.getElementos(), defensor.getElementos());
        double modificadorTerreno = terreno.modificarDano(ataque);
        double danoBase = (double) ataque.getAtaqueBase() * atacante.getAtaque() / defensor.getDefesa();
        return (int) Math.round(danoBase * multiplicadorTipo * modificadorTerreno);
    }
}


