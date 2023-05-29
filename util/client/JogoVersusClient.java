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
  String mensagemClient1, oponente, nickname, ehParOuImpar, retornar = "";
  int contadorRodada = 1;
  int numeroJogador, vitorias, derrotas;

  public void jogar(Socket socket) {
    try {
      scanner = new Scanner(socket.getInputStream());
      teclado = new Scanner(System.in);
      printWriter = new PrintWriter(socket.getOutputStream(), true);

      InputStream inputStream = socket.getInputStream();
      BufferedReader in = new BufferedReader(
          new InputStreamReader(inputStream));

      System.out.println("Aguardando jogador...");
      oponente = in.readLine();
      System.out.println("Seu oponente: " + oponente + "!");

      ehParOuImpar = in.readLine();

        System.out.println(ehParOuImpar);

        System.out.print("Digite um número de 0 à 5: ");
        numeroJogador = teclado.nextInt();
        printWriter.println(numeroJogador);

        if (numeroJogador < 0 || numeroJogador > 5) {
          System.out.println("Numero inválido, aguarde para jogar novamente...");
        }

        String respostaJogo = in.readLine();
        System.out.println(respostaJogo);
      
    } catch (Exception e) {
      System.out.println("Erro ao executar o jogo versus outro client");
    }
  }
}
