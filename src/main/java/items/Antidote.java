package items;
import enums.StatusDoEfeito;
import model.Pokesal;
public class Antidote implements Item{
    @Override
    public void aplicarEfeito(Pokesal pokesal){
        pokesal.setStatusEfeito(StatusDoEfeito.SEM_EFEITO);
    }
}
