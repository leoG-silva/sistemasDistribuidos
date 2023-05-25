package server;
import java.net.*;

import threads.ThreadJogo;

public class Server {
    public static void main(String[] args) {
        ServerSocket server;
        
        final int PORT = 12345;

        try {
            server = new ServerSocket(PORT);

            while (true) {
                System.out.println("Aguardando um cliente...");
                Socket client = server.accept();
                ThreadJogo jogo = new ThreadJogo(client);
                jogo.start();
            }
        } catch (Exception e) {
            System.out.println("Erro ao bindar com o cliente: " + e.getMessage());
        }
    }
}