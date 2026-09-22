package items;
import model.Pokesal;
public class SuperPotion implements Item {
    private static final int CURA_SUPER_POTION = 80;
    @Override
    public void aplicarEfeito(Pokesal pokesal){
        pokesal.curaPokemon(CURA_SUPER_POTION);
    }
}
