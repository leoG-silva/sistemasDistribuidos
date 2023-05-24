package server;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ThreadJogo extends Thread{
    private Socket client;
    Scanner scanner;
    PrintStream printStream;
    String msg = ""; 

    public ThreadJogo(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            System.out.println("Connected with " + client.getInetAddress().getHostAddress());  
        } catch (Exception e) {
            System.out.println("Error on thread: " + e.getMessage());
        }

        try {
            scanner = new Scanner(client.getInputStream());
            printStream = new PrintStream(client.getOutputStream());

            System.out.println("O servidor está sendo carregado...");
            try {
                Thread.sleep(2 * 1000);
              } catch (InterruptedException e) {}

              System.out.println("Jogo carregador. \r\n");

              try {
                Thread.sleep(2 * 1000);
              } catch (InterruptedException e) {}

            while(! msg.equals("sair")) {
                msg = scanner.nextLine();
                if(msg.equals("1")) {
                    System.out.println("O jogador escolheu a opção Jogador x Servidor");
                }
                if(msg.equals("2")) {
                    System.out.println("O jogador escolheu a opção Jogador x jogador");
                }
            }
        
        System.out.println("O jogador escolheu sair do jogo...");
        scanner.close();
        printStream.close();
        client.close();

        } catch (Exception e) {
            System.out.println("Erro na comunicação com o servidor");
        }
    }   
}