package model;
import enums.Elementos;
public class Pokesais {
    private static final int BULBASAL_VIDA = 300;
    private static final int BULBASAL_ATAQUE = 49;
    private static final int BULBASAL_DEFEFSA = 49;
    private static final int BULBASAL_VELOCIDADE = 45;
    private static final int BULBASAL_PODER_FRACO = 35;
    private static final int BULBASAL_PODER_FORTE = 55;

    private static final int CHARSAL_VIDA = 300;
    private static final int CHARSAL_ATAQUE = 52;
    private static final int CHARSAL_DEFESA = 43;
    private static final int CHARSAL_VELOCIDADE = 65;
    private static final int CHARSAL_PODER_FRACO = 40;
    private static final int CHARSAL_PODER_FORTE = 60;

    private static final int SQUIRTSAL_VIDA = 300;
    private static final int SQUIRTSAL_ATAQUE = 48;
    private static final int SQUIRTSAL_DEFESA = 65;
    private static final int SQUIRTSAL_VELOCIDADE = 43;
    private static final int SQUIRTSAL_PODER_FRACO = 35;
    private static final int SQUIRTSAL_PODER_FORTE = 55;

    private Pokesais(){
    }

    public static Pokesal Bubasal(){
        Ataque fraco = new Ataque("Chicote de Cipó", Elementos.PLANTA, BULBASAL_PODER_FRACO);
        Ataque forte = new Ataque("Folha Navalha", Elementos.PLANTA, BULBASAL_PODER_FORTE);
        return new Pokesal("Bubasal", BULBASAL_VIDA, BULBASAL_ATAQUE, BULBASAL_DEFEFSA, BULBASAL_VELOCIDADE,Elementos.PLANTA, fraco, forte);
    }
    public static Pokesal Charsal(){
        Ataque fraco = new Ataque("Brasa", Elementos.FOGO, CHARSAL_ATAQUE);
        Ataque forte = new Ataque("Lança Chamas", Elementos.FOGO, CHARSAL_ATAQUE);
        return new Pokesal("Charsal", CHARSAL_VIDA, CHARSAL_ATAQUE,CHARSAL_DEFESA, CHARSAL_VELOCIDADE, Elementos.FOGO, fraco, forte);
    }
    public static Pokesal SquirtSal() {
        Ataque fraco = new Ataque("Jato d'Água", Elementos.AGUA, SQUIRTSAL_PODER_FRACO);
        Ataque forte = new Ataque("Hidrobomba", Elementos.AGUA, SQUIRTSAL_PODER_FORTE);
        return new Pokesal("SquirtSal", SQUIRTSAL_VIDA, SQUIRTSAL_ATAQUE, SQUIRTSAL_DEFESA, SQUIRTSAL_VELOCIDADE, Elementos.AGUA, fraco, forte);
    }
}
