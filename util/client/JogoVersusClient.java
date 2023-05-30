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
  String nickname = "";
  String ehParOuImpar = "";
  String loopJogo = "";
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
      String oponente = in.readLine();
      System.out.println("Seu oponente: *" + oponente + "*!");

      ehParOuImpar = in.readLine();

      System.out.println(ehParOuImpar);

      do {
        System.out.print("Digite um número de 0 à 5: ");
        numeroJogador = teclado.nextInt();

        if (numeroJogador < 0 || numeroJogador > 5) {
          System.out.println("Numero inválido, aguarde para jogar novamente...");
        }
      } while (numeroJogador < 0 || numeroJogador > 5);

      printWriter.println(numeroJogador);

      try {
        for (int linhasRecebidas = 0; linhasRecebidas < 3; linhasRecebidas++) {
          String respostaJogo = in.readLine();
          System.out.println(respostaJogo);
        }
      } catch (Exception e) {
        System.out.println("Erro ao retornar resultados do servidor...");
      }

      // System.out.println("Deseja jogar novamente?");
      // System.out.println("S - Sim");
      // System.out.println("N - Não");

      // loopJogo = teclado.nextLine();
      // printWriter.println(loopJogo);

    } catch (Exception e) {
      System.out.println("Erro ao executar o jogo versus outro client");
    }
  }
}
