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

      System.out.println("O jogo está sendo carregado...");
      Thread.sleep(2000);
      System.out.println("Carregamento concluído.\r\n");
      Thread.sleep(2000);

      System.out.println("* BEM VINDO AO JOGO DE PAR OU IMPAR *");

      do {
        System.out.println("Escolha uma opção para seu jogo");
        System.out.println("1 - Jogador x Servidor");
        System.out.println("2 - Jogador x Jogador");
        System.out.println("S - Sair do jogo");

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
          case "S":
            break;
          default:
            System.out.println("Opção inválida... \r\n");
            Thread.sleep(2000);
            break;
        }
      } while (!mensagem.equalsIgnoreCase("S"));

      try {
        System.out.println("Saindo do jogo....");
        System.out.println("Fechando conexão");

        scanner.close();
        teclado.close();
        socket.close();

        System.out.println("Conexões fechadas.");
      } catch (Exception e) {
        System.out.println("Erro ao fechar conexão com o servidor...");
      }
    } catch (Exception e) {
      System.out.println("Erro ao conectar com o servidor: " + e.getMessage());
    }
  }
}
