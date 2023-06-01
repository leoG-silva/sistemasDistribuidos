package util.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class JogoVersusServidor {

  Socket socket;
  Scanner scanner, teclado;
  PrintWriter printWriter;
  String resultadoJogo = "";
  String mensagem = "";
  int contadorRodada = 1;
  int numeroJogador, vitorias, derrotas;

  public void executarjogo(Socket socket) {
    try {
      this.jogar(socket);

      System.out.println("Retornando ao menu principal... \r\n");
    } catch (Exception e) {
      System.out.println("Jogo não foi carregado corretamente...");
    }
  }

  public void jogar(Socket socket) {
    try {
      scanner = new Scanner(socket.getInputStream());
      teclado = new Scanner(System.in);
      printWriter = new PrintWriter(socket.getOutputStream(), true);

      InputStream inputStream = socket.getInputStream();
      BufferedReader in = new BufferedReader(
          new InputStreamReader(inputStream));

      do {
        System.out.println("Rodada " + contadorRodada);
        System.out.println("*** Escolha PAR ou IMPAR ***");
        System.out.println("P - Par");
        System.out.println("I - Impar");
        System.out.println("R - Retornar ao menu principal");

        mensagem = teclado.nextLine();
        printWriter.println(mensagem);

        switch (mensagem.toUpperCase()) {
          case "P":
            executarRodada(in);
            this.jogar(socket);
            break;
          case "I":
            executarRodada(in);
            this.jogar(socket);
            break;
          case "R":
            String resultadoFinal = in.readLine();
            System.out.println(resultadoFinal);
            break;
          default:
            System.out.println(
                "Nenhuma opção selecionada corretamente... \r\n");
            break;
        }
      } while (!mensagem.equalsIgnoreCase("R"));
    } catch (Exception e) {
      System.out.println("Erro ao executar o menu: " + e.getMessage());
    }
  }

  public void executarRodada(BufferedReader in) {
    System.out.print("Digite um número de 0 à 5: ");
    numeroJogador = teclado.nextInt();
    printWriter.println(numeroJogador);

    if (numeroJogador < 0 || numeroJogador > 5) {
      System.out.println("Numero inválido, aguarde para jogar novamente...");
      this.jogar(socket);
    }

    try {
      for (int linhasRecebidas = 0; linhasRecebidas < 5; linhasRecebidas++) {
        String resposta = in.readLine();
        System.out.println(resposta);
      }
    } catch (Exception e) {
      System.out.println("Erro ao retornar resultados do jogo vs servidor: " + e.getMessage());
    }

    contadorRodada++;
  }
}
