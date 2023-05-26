package util.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class JogoVersusClient {
  Socket socket;
  Scanner scanner, teclado;
  PrintWriter printWriter;
  String mensagemClient1 = "";
  String mensagemClient2 = "";
  String resultadoJogo = "";
  int contadorRodada = 1;
  int numeroJogador, vitorias, derrotas;

  public void jogar(Socket socket) {
    try {
      scanner = new Scanner(socket.getInputStream());
      teclado = new Scanner(System.in);
      printWriter = new PrintWriter(socket.getOutputStream(), true);
      
      InputStream inputStream = socket.getInputStream();
      BufferedReader in = new BufferedReader(
        new InputStreamReader(inputStream)
      );

      System.out.println("Client1, escolher par ou impar");
      mensagemClient1 = teclado.nextLine();
      printWriter.println(mensagemClient1);
      
      String resposta = in.readLine();
      System.out.println(resposta); 
      
      System.out.println("Client2, escolher par ou impar");
      mensagemClient2 = teclado.nextLine();
      printWriter.println(mensagemClient2);

      String resposta2 = in.readLine();
      System.out.println(resposta2);
        

      //conectar com outro jogador
      //escolher o player1 e player2 (pode ser por ordem de acesso ao jogo)
      //iniciar jogo
        //questionar jogada do jogador1 e guardar dado
        //questionar jogada do jogador2 e guardar dado
        //mostrar resultados na tela

    } catch (Exception e) {
      System.out.println("Erro ao executar o jogo versus outro client");
    }
  }
}
