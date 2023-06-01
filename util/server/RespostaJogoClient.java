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
  int numeroJogador, somaNumeros, respostaLoop, vitorias, contadorVitorias;
  private boolean par, jogadaOponente, ehPar, recebiMsgLoop, loop;

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
      System.out.println("O jogador *" + nickname + "* entrou na partida.");
      listaDeClientes.add(this);

      int index = listaDeClientes.indexOf(this);
      RespostaJogoClient oponente;

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

      do {
        System.out.println("");
        if (par) {
          printWriter.println("Você será o PAR!!!");
        } else {
          printWriter.println("Você será o IMPAR!!!");
        }
        
        String strNumeroJogador = scanner.nextLine();
        
        while(strNumeroJogador.isEmpty()) {
          strNumeroJogador = scanner.nextLine();
        }
        
        numeroJogador = Integer.parseInt(strNumeroJogador);
        jogadaOponente = true;

        while (true) {
          System.out.println("");
          if (oponente.jogadaOponente) {
            break;
          }
        }

        exibirNumerosServer(oponente);

        somaNumeros = numeroJogador + oponente.numeroJogador;
        ehPar = somaNumeros % 2 == 0;

        if(ehPar == par) {
          vitorias++;
        } 

        devolverResultadosClient(oponente);

        respostaLoop = scanner.nextInt();
        recebiMsgLoop = true;
        loop = true;

        while (true) {
          System.out.println(respostaLoop);
          if (oponente.recebiMsgLoop) {
            break;
          }
        }

        if (respostaLoop == 2 || oponente.respostaLoop == 2) {
          loop = false;
        } 

        printWriter.println(loop);

        oponente.recebiMsgLoop = false;
        numeroJogador = 0;
        somaNumeros = 0;
        jogadaOponente = false;
        ehPar = false;

      } while (loop);

      printWriter.println("*** Você ganhou " + vitorias + " vitorias ***");
      System.out.println(nickname + " saiu do jogo Jogador vs Jogador");
      listaDeClientes.remove(this);

    } catch (Exception e) {
      System.out.println("Erro ao executar jogo vs outro client: " + e.getMessage());
    }
  }

  public void exibirNumerosServer(RespostaJogoClient oponente) {
    System.out.println(nickname + " jogou " + numeroJogador);
    System.out.println(oponente.nickname + " jogou " + oponente.numeroJogador);
    System.out.println("Somando valores....");
  }

  public void devolverResultadosClient(RespostaJogoClient oponente) {
    printWriter.println("O jogador *" + nickname + "* jogou numero " + numeroJogador);
    printWriter.println("O jogador *" + oponente.nickname + "* jogou numero " + oponente.numeroJogador);
    printWriter.println("O jogador *" + (ehPar == par ? nickname : oponente.nickname) + "* venceu");
  }
}