package threads;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import util.server.RespostaJogoClient;
import util.server.RespostaJogoServidor;

public class ThreadJogo extends Thread {

  private Socket client;
  Scanner scanner;
  PrintWriter printWriter;
  String msg = "";
  String nickname = "";


  public ThreadJogo(Socket client) {
    try {
      this.client = client;
      scanner = new Scanner(client.getInputStream());
      printWriter = new PrintWriter(client.getOutputStream(), true);
    } catch (Exception e) {
      System.out.println("Erro no construtros do ThreadJogo");
    }

  }

  @Override
  public void run() {
    //iniciando conexão
    try {
      System.out.println("\r\n Conectado a porta " + client.getLocalPort());
    } catch (Exception e) {
      System.out.println("Error on thread: " + e.getMessage());
    }

    //iniciando jogo
    try {
      System.out.println("O servidor está sendo carregado...");
      Thread.sleep(2000);
      System.out.println("Jogo carregado. \r\n");
      Thread.sleep(2000);

      nickname = scanner.nextLine();

      System.out.println("O jogador " + nickname + " iniciou o jogo");

      while (!msg.equalsIgnoreCase("S")) {
        msg = scanner.nextLine();
        if (msg.equalsIgnoreCase("1")) {
          System.out.println("O jogador " + nickname + " escolheu a opção Jogador x Servidor");
          RespostaJogoServidor jogoVsServidor = new RespostaJogoServidor(client, nickname);
          jogoVsServidor.executarJogoVersusServidor();
        }
          if (msg.equalsIgnoreCase("2")) {
          System.out.println("O jogador " + nickname + " escolheu a opção Jogador x Jogador");

          RespostaJogoClient jogoVsClient = new RespostaJogoClient(client, nickname);
          jogoVsClient.run();
        }

        if (msg.equalsIgnoreCase("S")){
          System.out.println("O jogador " + nickname + " escolheu sair do jogo...");
        }
      }
    } catch (Exception e) {
      System.out.println("Erro na comunicação com o cliente");
    }

    //etapa de encerramento
    try {
      System.out.println(
        "Encerrando conexão com client " +
        client.getInetAddress().getHostAddress()
      );

      scanner.close();
      printWriter.close();
      client.close();
    } catch (Exception e) {
      System.out.println("Erro ao fechar a conexão com o cliente.");
    }
  }
}
