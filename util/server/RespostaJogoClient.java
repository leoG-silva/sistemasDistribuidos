package util.server;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RespostaJogoClient implements Runnable {
  private static final List<Socket> listaDeClientes = new ArrayList<>();
  private Socket client;
  Scanner scanner;
  PrintWriter printWriter;
  String msgClient1 = "";
  String msgClient2 = "";
  String nickname = "";
  private int numeroJogador = 10;
  int numeroServer, somaNumeros, numeroClient, vitorias, derrotas, par, impar;
  
  public RespostaJogoClient(Socket client, String nickname) {
      try {
        this.client = client;
        this.nickname = nickname;
        scanner = new Scanner(client.getInputStream());
        printWriter = new PrintWriter(client.getOutputStream(), true);
      } catch (Exception e) {
        System.out.println("Erro no construtor do respostaJogoClient");
      }
  }

  @Override
  public void run() {
    try {
      System.out.println("O jogador " + nickname + " entrou na partida.");
      listaDeClientes.add(client);
      
      int index = listaDeClientes.indexOf(client); 
      Socket oponente;
      while (true) {
        System.out.println("");
        if(listaDeClientes.size() % 2 == 0) {
          oponente = index % 2 == 0 ? listaDeClientes.get(index + 1) : listaDeClientes.get(index - 1);
        }
      }

      printWriter.println("O seu oponente é " + oponente);
      //jogador1 conectou
      //jogador2 conectou
      //receber dado jogador1

      //msgClient1 = scanner.nextLine();
      //printWriter.println("Devolvendo mensagem");
  
      //receber dado jogador2
      //verificar vencedor
    } catch (Exception e) {
      System.out.println("erro");
    }
  }    
}