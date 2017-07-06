package ita.q4p2ces29;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VulnerableClass {

  static final int TAM_MAX_LINHA = 100;

  /**
   * Multiple lines of Javadoc text are written here,
   * wrapped normally...
   */
  public void vulnerableMethod(String file) throws IOException {

    //Verifica se file é seguro
    Pattern pattern = Pattern.compile("[^A-Za-z0-9._]");
    Matcher matcher = pattern.matcher(file);
    if (matcher.find()) {
      throw new IOException();
    }

    int contador = 0;
    while (contador == 0) {
      contador++;
      Scanner console = new Scanner(System.in);
      System.out.print("Digite a operacao desejada para realizar no arquivo");
      System.out.print(" <R para ler um arquivo, W para escrever em um arquivo>? ");

      String opr = console.nextLine();

      if (opr.equals("R")) { //verifica se digitou R
        BufferedReader br = null;
        FileReader fr = null;

        fr = new FileReader(file);
        br = new BufferedReader(fr);

        String scurrentline;
        char[] buffer = new char[TAM_MAX_LINHA];

        br = new BufferedReader(new FileReader(file));

        while (br.read(buffer, 0, TAM_MAX_LINHA) != -1) {
          scurrentline = String.valueOf(buffer);
          System.out.print(scurrentline);
          buffer = new char[TAM_MAX_LINHA];
        }
        
        fr.close();
        br.close();
      } else if (opr.equals("W")) { //verifica se digitou W
        BufferedWriter buffWrite;

        buffWrite = new BufferedWriter(new FileWriter(file));
        String linha = "";
        System.out.println("Escreva algo: ");
        linha = console.nextLine();

        if (linha.length() > TAM_MAX_LINHA) {
          char[] buffer = linha.toCharArray();
          linha = String.valueOf(buffer, 0, TAM_MAX_LINHA);
        }

        buffWrite.append(linha + "\n");
        //System.out.println(linha);
        
        buffWrite.close();

      } else { //se não digitou R nem W lança exceção
        throw new IllegalArgumentException();
      }
    }
  }
}
