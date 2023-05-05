package server;
import java.net.Socket;

public class ThreadJogo extends Thread{
    private Socket client;

    public ThreadJogo(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            System.out.println("Connected with " + client.getInetAddress().getHostAddress());
            client.close();
            
        } catch (Exception e) {
            System.out.println("Error on thread: " + e.getMessage());
        }
    }
}