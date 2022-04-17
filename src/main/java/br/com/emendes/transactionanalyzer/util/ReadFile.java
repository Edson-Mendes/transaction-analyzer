package br.com.emendes.transactionanalyzer.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.web.multipart.MultipartFile;

public abstract class ReadFile {

  /**
   * Método que lê um MultipartFile e devolver uma lista com todas as linhas do
   * arquivo.
   * 
   * @return Uma lista com as linhas do arquivo
   */
  public static List<String> readMultipartFile(MultipartFile file) {
    List<String> transactions = new ArrayList<>();
    try (Scanner input = new Scanner(file.getInputStream())) {
      while (input.hasNextLine()) {
        transactions.add(input.nextLine());
      }
    } catch (IOException e) {
      // TODO: Tratar a exception
      System.out.println("NÃO FOI POSSÍVEL LER O ARQUIVO!");
    }

    return transactions;
  }

  private static boolean validTransactionFields(String[] transactionFields) {
    // TODO: deletar caso não seja usado
    return false;
  }

}
