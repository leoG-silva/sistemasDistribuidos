package server;

import java.net.ServerSocket;
import java.net.Socket;

import threads.ThreadJogo;

public class Server {

  public static void main(String[] args) {
    ServerSocket server;
    Socket socket = null;
    int porta = 54321;
    boolean continuar = true;

    try {
      server = new ServerSocket(porta);
      System.out.println("Servidor disponível na porta: " + porta);
    } catch (Exception e) {
      System.out.println("Erro: " + e.getMessage());
      return;
    }

    while (continuar) {
      try {
        System.out.println("Aguardando o cliente...");
        socket = server.accept();
        System.out.println("Conectado com " + socket.getInetAddress().getHostAddress());

        ThreadJogo jogo = new ThreadJogo(socket);
        jogo.start();
      } catch (Exception e) {}
    }

    try {
      System.out.println("Encerrando o servidor");
      socket.close();
      server.close();
    } catch (Exception e) {}
  }
}
