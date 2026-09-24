import batalha.GerencidorDaBatalha;
import items.Antidote;
import items.Item;
import items.Potion;
import items.SuperPotion;
import java.util.Scanner;
import model.Ataque;
import model.Pokesais;
import model.Pokesal;
import model.Treinador;
import terreno.AsfaltoQuente;
import terreno.EfeitoDoTerreno;

/**
 * Classe principal responsável por executar a simulação de batalha do PokeSal via console.
 */
public class Main {

  /**
   * Ponto de inicio: executa a batalha entre dois treinadores
   * até ter um vencedor.
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    Pokesal charSal = Pokesais.charsal();
    Pokesal bubasal = Pokesais.bubasal();

    Treinador treinador1 = new Treinador("Ash", charSal);
    Treinador treinador2 = new Treinador("Brook", bubasal);

    EfeitoDoTerreno terreno = new AsfaltoQuente();
    GerencidorDaBatalha gerencidorDaBatalha =
        new GerencidorDaBatalha(treinador1, treinador2, terreno);

    int turno = 1;
    while (!gerencidorDaBatalha.batalhaTerminou()) {
      System.out.println("--- Turno " + turno + " ---");

      Treinador atacante = gerencidorDaBatalha.recalculaAtacantePrioritario();
      Treinador defensor = gerencidorDaBatalha.getOponente(atacante);

      executarTurnoDoTreinador(scanner, gerencidorDaBatalha, atacante, defensor);

      if (defensor
          .getPokesal()
          .pokesalestaVivo()) {
        executarTurnoDoTreinador(scanner, gerencidorDaBatalha, defensor, atacante);
      }
      gerencidorDaBatalha.finalizarTurno();
      System.out.println(treinador1.getNome() + " (" + charSal.getNome() + "): "
          + charSal.getVidaAtual() + " Vida | Status: " + charSal.getStatusDoEfeito());
      System.out.println(treinador2.getNome() + " (" + bubasal.getNome() + "): "
          + bubasal.getVidaAtual() + " Vida | Status: " + bubasal.getStatusDoEfeito());
      turno++;
    }

    Treinador vencedor = gerencidorDaBatalha.getVencedor();
    if (vencedor != null) {
      System.out.println(vencedor.getNome() + " venceu a batalha!");
    } else {
      System.out.println("Empate.");
    }

    scanner.close();
  }

  private static void executarTurnoDoTreinador(Scanner scanner,
                                               GerencidorDaBatalha gerencidorDaBatalha,
                                               Treinador quemAge, Treinador oponente) {
    boolean querUsarItem = quemAge.podeUsarItem()
        && perguntarSimNao(scanner, quemAge.getNome() + ", usar um item em vez de atacar? (s/n): ");

    if (querUsarItem) {
      usarItem(scanner, quemAge);
      return;
    }

    Ataque ataqueEscolhido = escolherAtaque(scanner, quemAge);

    boolean oponenteUsaProtecao = false;
    if (oponente.estaProtecaoDisponivel()) {
      oponenteUsaProtecao = perguntarSimNao(scanner,
          oponente.getNome() + ", usar proteção para anular esse ataque? (s/n): ");
    }

    gerencidorDaBatalha.executarAtaque(quemAge, ataqueEscolhido, oponenteUsaProtecao);
  }

  private static boolean perguntarSimNao(Scanner scanner, String pergunta) {
    System.out.print(pergunta);
    return scanner
        .next()
        .equalsIgnoreCase("s");
  }

  private static void usarItem(Scanner scanner, Treinador treinador) {
    System.out.println("1 - Potion (cura 40)");
    System.out.println("2 - Super Potion (cura 80)");
    System.out.println("3 - Antidote (remove status)");
    System.out.print("Escolha o item: ");

    int escolha = scanner.nextInt();
    Item item;
    if (escolha == 2) {
      item = new SuperPotion();
    } else if (escolha == 3) {
      item = new Antidote();
    } else {
      item = new Potion();
    }

    treinador.usarItem(item);
  }

  private static Ataque escolherAtaque(Scanner scanner, Treinador treinador) {
    Pokesal pokesal = treinador.getPokesal();
    Ataque ataque1 = pokesal.getAtk1();
    Ataque ataque2 = pokesal.getAtk2();

    System.out.println(treinador.getNome() + " (" + pokesal.getNome() + "), escolha o ataque:");
    System.out.println("1 - " + ataque1.getNome() + textoBloqueado(pokesal, ataque1));
    System.out.println("2 - " + ataque2.getNome() + textoBloqueado(pokesal, ataque2));

    Ataque escolhido = null;
    while (escolhido == null) {
      System.out.print("Digite 1 ou 2: ");
      int escolha = scanner.nextInt();

      Ataque candidato = null;
      if (escolha == 1) {
        candidato = ataque1;
      } else if (escolha == 2) {
        candidato = ataque2;
      }

      if (candidato == null) {
        System.out.println("Opção inválida.");
      } else if (!pokesal.ataqueDisponivel(candidato)) {
        System.out.println("Esse ataque foi usado no turno anterior, escolha o outro.");
      } else {
        escolhido = candidato;
      }
    }
    return escolhido;
  }

  private static String textoBloqueado(Pokesal pokesal, Ataque ataque) {
    if (pokesal.ataqueDisponivel(ataque)) {
      return "";
    }
    return " (bloqueado, repetido)";
  }
}