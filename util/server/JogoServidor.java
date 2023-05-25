package util.server;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class JogoServidor {

  private Socket client;
  Scanner scanner;
  PrintWriter printWriter;
  String msg = "";
  int numeroServer, somaNumeros, numeroClient;

  public JogoServidor(Socket client) {
    this.client = client;
  }

  public void executarJogoVersusServidor() {
    System.out.println("Jogador acessou a opção JOGADOR X SERVIDOR...");

    try {
      scanner = new Scanner(client.getInputStream());
      printWriter = new PrintWriter(client.getOutputStream(), true);
      
      while (!msg.equalsIgnoreCase("Retornar")) {
        msg = scanner.nextLine();

        if (msg.equalsIgnoreCase("P")) {
          System.out.println("O jogador escolheu a opção PAR");
          numeroClient = scanner.nextInt();
          numeroServer = this.jogarServer();
          somaNumeros = numeroServer + numeroClient;

          this.mostrarNumeros(numeroClient, numeroServer, somaNumeros);
          
          if(somaNumeros % 2 == 0) {
            printWriter.println("O jogador venceu");
            System.out.println("O jogador venceu");
          } else {
            printWriter.println("O jogador perdeu");
            System.out.println("O jogador perdeu");
          }

        }
          if (msg.equalsIgnoreCase("I")) {
          System.out.println("O jogador escolheu a opção IMPAR");
          numeroClient = scanner.nextInt();
          numeroServer = this.jogarServer();
          somaNumeros = numeroServer + numeroClient;

          this.mostrarNumeros(numeroClient, numeroServer, somaNumeros);

          if(somaNumeros % 2 == 1) {
            printWriter.println("O jogador venceu");
            System.out.println("O jogador venceu");
          } else {
            printWriter.println("O jogador perdeu");
            System.out.println("O jogador perdeu");
          }
        }

        if(msg.equalsIgnoreCase("RETORNAR")) {
          System.out.println("Voltando ao menu inicial... ");
        }
      }
    } catch (Exception e) {
      System.out.println("Erro na comunicação com o servidor");
    }
  }

  public void mostrarNumeros(int numeroClient, int numeroServer, int somaNumeros ) {
    printWriter.println("Numero do jogador = " + numeroClient);
    printWriter.println("Numero do servidor = " + numeroServer);
    printWriter.println("Somatória dos valores = " + somaNumeros);
  }

  public int jogarServer() {
    Random random = new Random();
    int numeroServer = random.nextInt(6);
    return numeroServer;
  }
}
