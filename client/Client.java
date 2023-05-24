package client;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

  public static void main(String[] args) {
    Socket socket;
    Scanner scanner, teclado;
    PrintStream printStream;
    String mensagem = "";
    final String HOST = "localhost";
    final int PORT = 12345;

    try {
      socket = new Socket(HOST, PORT);
      printStream = new PrintStream(socket.getOutputStream());
      scanner = new Scanner(socket.getInputStream());
      teclado = new Scanner(System.in);

      try {
        System.out.println("O jogo está sendo carregado...");

        try {
          Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {}

        System.out.println("Carregamento concluído.\r\n");

        try {
          Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {}

        while (!mensagem.equals("Sair")) {
          System.out.println("Escolha uma opção para seu jogo");
          System.out.println("1 -Jogador x Servidor");
          System.out.println("2 -Jogador x Jogador");
          System.out.println("Sair - Sair do jogo");

          mensagem = teclado.nextLine();
          printStream.println(mensagem);

          switch (mensagem) {
            case "1":
              //return threadJogoVSServidor

              break;
            case "2":
              //return threadJogoVSjogador

              break;
            case "sair":
              System.out.println("Saindo do jogo....");

              try {
                Thread.sleep(2 * 1000);/* 2 segundos de load */
              } catch (InterruptedException e) {}

              break;
            default:
              System.out.println("Opção invalida... ");

              try {
                Thread.sleep(2 * 1000);/* 2 segundos de load */
              } catch (InterruptedException e) {}

              break;
          }
        }
      } catch (Exception e) {
        System.out.println("Erro ao executar o menu do jogo...");
      }

      scanner.close();
      teclado.close();
      socket.close();
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
