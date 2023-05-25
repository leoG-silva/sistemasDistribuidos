package util.client;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class JogoVersusServidor {

  Socket socket;
  Scanner scanner, teclado;
  PrintStream printStream;
  String mensagem = "";
  String resultadoJogo = "";
  int numeroJogador;

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
      do {
        printStream = new PrintStream(socket.getOutputStream());
        scanner = new Scanner(socket.getInputStream());
        teclado = new Scanner(System.in);

        System.out.println("*** Escolha PAR ou IMPAR ***");
        System.out.println("P - Par");
        System.out.println("I - Impar");
        System.out.println("Retornar - Retornar ao menu principal");

        mensagem = teclado.nextLine();
        printStream.println(mensagem);

        switch (mensagem) {
          case "P":
          case "p":
            System.out.print("Digite um número de 0 à 5: ");
            numeroJogador = teclado.nextInt();
            printStream.println(numeroJogador);

            //resultadoJogo = scanner.nextLine();
            printStream.println(resultadoJogo);

            if (numeroJogador < 0 || numeroJogador > 5) {
              System.out.println(
                "Numero inválido, aguarde para jogar novamente..."
              );
              this.jogar(socket);
            }

            this.jogar(socket);
            break;

          case "I":
          case "i":
            System.out.print("Digite um número de 0 à 5: ");
            numeroJogador = teclado.nextInt();
            printStream.println(numeroJogador);

            //resultadoJogo = scanner.nextLine();
            printStream.println(resultadoJogo);

            if (numeroJogador < 0 || numeroJogador > 5) {
              System.out.println(
                "Numero inválido, aguarde para jogar novamente..."
              );
              this.jogar(socket);
            }

            this.jogar(socket);
            break;

          case "retornar":
          case "Retornar":
            break;

          default:
            System.out.println(
              "Nenhuma opção selecionada corretamente... \r\n"
            );
            break;
        }
      } while (!mensagem.equalsIgnoreCase("Retornar"));
    } catch (Exception e) {
      System.out.println("Erro ao executar o menu...");
    }
  }
}
