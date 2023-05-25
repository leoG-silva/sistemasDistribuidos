package client;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import util.client.JogoVersusServidor;

public class Client {

  public static void main(String[] args) {
    Socket socket;
    Scanner scanner, teclado;
    PrintWriter printWriter;
    String mensagem = "";
    final String HOST = "localhost";
    final int PORT = 12345;

    try {
      socket = new Socket(HOST, PORT);
      printWriter = new PrintWriter(socket.getOutputStream(), true);
      scanner = new Scanner(socket.getInputStream());
      teclado = new Scanner(System.in);

      try {
        System.out.println("O jogo está sendo carregado...");

        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {}

        System.out.println("Carregamento concluído.\r\n");

        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {}

        do {
          System.out.println("***BEM VINDO AO JOGO DE PAR OU IMPAR ***");
          System.out.println("Escolha uma opção para seu jogo");
          System.out.println("1 - Jogador x Servidor");
          System.out.println("2 - Jogador x Jogador");
          System.out.println("Sair - Sair do jogo");

          mensagem = teclado.nextLine();
          printWriter.println(mensagem);

          switch (mensagem.toUpperCase()) {
            case "1":
              JogoVersusServidor jogoVersusServidor = new JogoVersusServidor();
              jogoVersusServidor.executarjogo(socket);
              break;
            case "2":
              //return executarJogoVersusClient()
              break;

            case "SAIR":
              break;

            default:
              System.out.println("Opção invalida... \r\n");

              try {
                Thread.sleep(2000);
              } catch (InterruptedException e) {}

              break;
          }
        } while (!mensagem.equalsIgnoreCase("SAIR"));
      } catch (Exception e) {
        System.out.println("Erro ao executar o menu do jogo...");
      }

      System.out.println("Saindo do jogo....");

      try {
        System.out.println("Fechando conexão");

        scanner.close();
        teclado.close();
        socket.close();

        System.out.println("Conexões fechadas.");
      } catch (Exception e) {
        System.out.println("Erro ao fechar conexão");
      }


    } catch (Exception e) {
      System.out.println("Error ao bindar com o servidor: " + e.getMessage());
    }
  }
}
