package br.com.emendes.transactionanalyzer.util;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.web.multipart.MultipartFile;

public abstract class ReadFile {

  public static void readMultipartFile(MultipartFile file) {
    try (Scanner input = new Scanner(file.getInputStream())) {
      System.out.println("===================================================");
      System.out.println("File name: " + file.getOriginalFilename());
      System.out.println("File size: " + file.getSize() + " bytes");
      System.out.println("===================================================");

      System.out.println("---------------------------------------------------------");
      while (input.hasNextLine()) {
        System.out.println(input.nextLine());
      }
      System.out.println("---------------------------------------------------------");
    } catch (IOException e) {
      // TODO: Tratar a exception
      System.out.println("NÃO FOI POSSÍVEL LER O ARQUIVO!");
    }
  }

}
