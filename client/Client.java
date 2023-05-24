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
          Thread.sleep(2000);
        } catch (InterruptedException e) {}

        System.out.println("Carregamento concluído.\r\n");

        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {}

        do {
          System.out.println("Escolha uma opção para seu jogo");
          System.out.println("1 - Jogador x Servidor");
          System.out.println("2 - Jogador x Jogador");
          System.out.println("Exit - Sair do jogo");

          mensagem = teclado.nextLine();
          printStream.println(mensagem);

         
          switch (mensagem) {
            case "1":
              //return executarJogoVersusServidor()
              break;
            case "2":
              //return executarJogoVersusClient()
              break;

            case "exit":
              break;

            case "Exit":
              break;

            default:
              System.out.println("Opção invalida... \r\n");

              try {
                Thread.sleep(2000);
              } catch (InterruptedException e) {}

              break;
          }
        } while (!mensagem.equalsIgnoreCase("Exit"));
      } catch (Exception e) {
        System.out.println("Erro ao executar o menu do jogo...");
      }

      System.out.println("Saindo do jogo....");

      scanner.close();
      teclado.close();
      socket.close();
    } catch (Exception e) {
      System.out.println("Error ao bindar com o servidor: " + e.getMessage());
    }
  }
}
