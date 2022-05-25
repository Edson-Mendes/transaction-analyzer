package br.com.emendes.transactionanalyzer.util;

import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

public class MultipartFileCreator {
  public static String path = "src/test/java/br/com/emendes/transactionanalyzer/util/file/";

  public static MultipartFile validFile() {
    String fileName = "validFile.csv";
    try {
      InputStream inputStream = new FileInputStream(path + fileName);

      MultipartFile file = new MockMultipartFile(
          "transaction.csv",
          fileName,
          "text",
          inputStream);

      return file;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public static MultipartFile fileWithOneTransaction() {
    try {
      String fileName = "singleTransactionFile.csv";
      InputStream inputStream = new FileInputStream(path + fileName);

      MultipartFile file = new MockMultipartFile(
          "transaction.csv",
          fileName,
          "text",
          inputStream);

      return file;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public static MultipartFile invalidFileType() {
    try {
      String fileName = "filetxt.txt";
      InputStream inputStream = new FileInputStream(path + fileName);

      MultipartFile file = new MockMultipartFile(
          "filetxt.txt",
          fileName,
          "text",
          inputStream);

      return file;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public static MultipartFile emptyFile() {
    try {
      String fileName = "emptyFile.csv";
      InputStream inputStream = new FileInputStream(path + fileName);

      MultipartFile file = new MockMultipartFile(
          "emptyFile.csv",
          fileName,
          "text",
          inputStream);

      return file;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public static MultipartFile biggerFile() {
    try {
      String fileName = "biggerFile.csv";
      InputStream inputStream = new FileInputStream(path + fileName);

      MultipartFile file = new MockMultipartFile(
          "transaction.csv",
          fileName,
          "text",
          inputStream);

      return file;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public static MultipartFile invalidTransaction() {
    try {
      String fileName = "invalidFile.csv";
      InputStream inputStream = new FileInputStream(path + fileName);

      MultipartFile file = new MockMultipartFile(
          "transaction.csv",
          fileName,
          "text",
          inputStream);

      return file;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public static MultipartFile fileSeparatedBySemicolon() {
    try {
      String fileName = "semicolonSeparator.csv";
      InputStream inputStream = new FileInputStream(path + fileName);

      MultipartFile file = new MockMultipartFile(
          "transaction.csv",
          fileName,
          "text",
          inputStream);

      return file;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

}
