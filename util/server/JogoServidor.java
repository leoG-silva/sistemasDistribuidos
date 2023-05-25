package util.server;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class JogoServidor {

  private Socket client;
  Scanner scanner;
  PrintStream printStream;
  String msg = "";
  int numeroServer, somaNumeros, numeroClient;

  public JogoServidor(Socket client) {
    this.client = client;
  }

  public void executarJogoVersusServidor() {
    System.out.println("Jogador acessou a opção JOGADOR X SERVIDOR...");

    try {
      scanner = new Scanner(client.getInputStream());
      printStream = new PrintStream(client.getOutputStream());
      
      while (!msg.equalsIgnoreCase("Retornar")) {
        msg = scanner.nextLine();

        if (msg.equalsIgnoreCase("P")) {
          System.out.println("O jogador escolheu a opção PAR");
          numeroClient = scanner.nextInt();
          numeroServer = this.jogarServer();
          somaNumeros = numeroServer + numeroClient;
          System.out.println("Numero do cliente = " + numeroClient);
          System.out.println("Numero do servidor = " + numeroServer);
          System.out.println("Somatória dos valores = " + somaNumeros);

          if(somaNumeros % 2 == 0) {
            System.out.println("O jogador venceu");
          } else {
            System.out.println("O jogador perdeu");
          }
        }
          if (msg.equalsIgnoreCase("I")) {
          System.out.println("O jogador escolheu a opção IMPAR");
          numeroClient = scanner.nextInt();
          numeroServer = this.jogarServer();
          somaNumeros = numeroServer + numeroClient;
          System.out.println("Numero do jogador = " + numeroClient);
          System.out.println("Numero do servidor = " + numeroServer);
          System.out.println("Somatória dos valores = " + somaNumeros);

          if(somaNumeros % 2 == 1) {
            System.out.println("O jogador venceu");
          } else {
            System.out.println("O jogador perdeu");
          }
        }
      }
    } catch (Exception e) {
      System.out.println("Erro na comunicação com o servidor");
    }
  }

  public int jogarServer() {
    Random random = new Random();
    int numeroServer = random.nextInt(6);
    return numeroServer;
  }
}
