package util.server;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RespostaJogoClient implements Runnable {
  private static final List<RespostaJogoClient> listaDeClientes = new ArrayList<>();
  Scanner scanner;
  PrintWriter printWriter;
  String nickname = "";
  String continuarJogando = "";
  String loopJogo = "";
  int numeroJogador, somaNumeros, vitorias, derrotas;
  private boolean par, jogadaOponente, ehPar;

  public RespostaJogoClient(Socket client, String nickname) {
    try {
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
      // jogador1 conectou
      System.out.println("O jogador *" + nickname + "* entrou na partida.");
      listaDeClientes.add(this);

      int index = listaDeClientes.indexOf(this);
      RespostaJogoClient oponente;

      // aguardando conexão jogador2 e selecionando oponente
      while (true) {
        System.out.println("");
        if (listaDeClientes.size() % 2 == 0) {
          oponente = index % 2 == 0 ? listaDeClientes.get(index + 1) : listaDeClientes.get(index - 1);
          break;
        }
      }

      printWriter.println(oponente.nickname);
      listaDeClientes.get(0).par = true;
      listaDeClientes.get(1).par = false;

      if (par) {
        printWriter.println("Você será o PAR!!!");
      } else {
        printWriter.println("Você será o IMPAR!!!");
      }

      // while (!loopJogo.equalsIgnoreCase("N")) {
        verificarVencedor(oponente);

        // loopJogo = scanner.nextLine();

        // while (true) {
        //   if (loopJogo.equalsIgnoreCase("N"))
            System.out.println(nickname + " Saiu");
            listaDeClientes.remove(this);
          // break;
        // }
      // }
    } catch (Exception e) {
      System.out.println("Erro ao executar jogo contra outro Client");
    }
  }

  public void verificarVencedor(RespostaJogoClient oponente) {
    numeroJogador = Integer.parseInt(scanner.nextLine());
    jogadaOponente = true;

    while (true) {
      System.out.println("");
      if (oponente.jogadaOponente) {
        break;
      }
    }

    System.out.println(nickname + " jogou " + numeroJogador);
    System.out.println(oponente.nickname + " jogou " + oponente.numeroJogador);
    System.out.println("Somando valores....");

    somaNumeros = numeroJogador + oponente.numeroJogador;
    ehPar = somaNumeros % 2 == 0;

    printWriter.println("O jogador *" + nickname + "* jogou numero " + numeroJogador);
    printWriter.println("O jogador *" + oponente.nickname + "* jogou numero " + oponente.numeroJogador);
    printWriter.println("O jogador *" + (ehPar == par ? nickname : oponente.nickname) + "* venceu");
  }
}