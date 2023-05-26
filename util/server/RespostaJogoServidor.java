package util.server;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class RespostaJogoServidor {

  private Socket client;
  Scanner scanner;
  PrintWriter printWriter;
  String msg = "";
  String nickname = "";
  int numeroServer, somaNumeros, numeroClient, vitorias, derrotas, par, impar;
  
  public RespostaJogoServidor(Socket client, String nickname) {
    this.client = client;
    this.nickname = nickname;
  }

  public void executarJogoVersusServidor() {
    System.out.println("O jogador " + nickname + " acessou a opção JOGADOR X SERVIDOR...");

    try {
      scanner = new Scanner(client.getInputStream());
      printWriter = new PrintWriter(client.getOutputStream(), true);

      while (!msg.equalsIgnoreCase("Retornar")) {
        msg = scanner.nextLine();

        if (msg.equalsIgnoreCase("P")) {
          System.out.println("O jogador " + nickname + " escolheu a opção PAR");
          numeroClient = scanner.nextInt();
          par = 0;
          mostrarResultado(numeroClient, par);
        }

        if (msg.equalsIgnoreCase("I")) {
          System.out.println("O jogador " + nickname + " escolheu a opção IMPAR");
          numeroClient = scanner.nextInt();
          impar = 1;
          mostrarResultado(numeroClient, impar);
        }

        if (msg.equalsIgnoreCase("R")) {
          printWriter.println("Você obteve " + vitorias + " vitorias e " + derrotas + " derrotas");
          System.out.println("O jogador " + nickname + " está voltando ao menu inicial... ");
          vitorias = 0;
          derrotas = 0;
        }
      }
    } catch (Exception e) {
      System.out.println("Erro na comunicação com o servidor");
    }
  }

  public void mostrarValores(
    int numeroClient,
    int numeroServer,
    int somaNumeros
  ) {
    printWriter.println("Numero do jogador = " + numeroClient);
    printWriter.println("Numero do servidor = " + numeroServer);
    printWriter.println("Somatória dos valores = " + somaNumeros);
  }

  public int jogarServer() {
    Random random = new Random();
    int numeroServer = random.nextInt(6);
    return numeroServer;
  }

  public void mostrarResultado(int numeroClient, int parOuImpar) {
    numeroServer = this.jogarServer();
    somaNumeros = numeroServer + numeroClient;

    this.mostrarValores(numeroClient, numeroServer, somaNumeros);

    if (somaNumeros % 2 == parOuImpar) {
      vitorias++;
      printWriter.println("O jogador venceu");
      printWriter.println(
        "Você tem " + vitorias + " vitorias e " + derrotas + " derrotas"
      );
      System.out.println("O jogador venceu");
    } else {
      derrotas++;
      printWriter.println("O jogador perdeu");
      printWriter.println(
        "Você tem " + vitorias + " vitorias e " + derrotas + " derrotas"
      );
      System.out.println("O jogador perdeu");
    }
  }
}
