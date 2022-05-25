package br.com.emendes.transactionanalyzer.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.web.multipart.MultipartFile;

import br.com.emendes.transactionanalyzer.exception.CouldNotReadFileException;
import br.com.emendes.transactionanalyzer.exception.InvalidFileException;

// FIXME: DELETAR
public abstract class ReadFile {

  /**
   * Método que lê um MultipartFile e devolver uma lista com todas as linhas do
   * arquivo.
   * 
   * @return Uma lista com as linhas do arquivo
   * @throws InvalidFileException se o arquivo estiver vazio
   */
  public static List<String> readMultipartFile(MultipartFile file) {
    List<String> transactions = new ArrayList<>();
    try (Scanner input = new Scanner(file.getInputStream())) {
      while (input.hasNextLine()) {
        transactions.add(input.nextLine());
      }
    } catch (IOException e) {
      String message = String.format("Could not read the file %s", file.getOriginalFilename());
      throw new CouldNotReadFileException(message);
    }

    return transactions;
  }

}
