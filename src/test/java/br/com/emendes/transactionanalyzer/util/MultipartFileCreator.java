package br.com.emendes.transactionanalyzer.util;

import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

public class MultipartFileCreator {

  public static MultipartFile validFile() {
    try {
      String path = "src/test/java/br/com/emendes/transactionanalyzer/util/file/validFile.csv";
      InputStream inputStream = new FileInputStream(path);

      MultipartFile file = new MockMultipartFile(
          "transaction.csv",
          "validFile.csv",
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
      String path = "src/test/java/br/com/emendes/transactionanalyzer/util/file/filetxt.txt";
      InputStream inputStream = new FileInputStream(path);

      MultipartFile file = new MockMultipartFile(
          "filetxt.txt",
          "filetxt.txt",
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
      String path = "src/test/java/br/com/emendes/transactionanalyzer/util/file/emptyFile.csv";
      InputStream inputStream = new FileInputStream(path);

      MultipartFile file = new MockMultipartFile(
          "emptyFile.csv",
          "emptyFile.csv",
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
      String path = "src/test/java/br/com/emendes/transactionanalyzer/util/file/biggerFile.csv";
      InputStream inputStream = new FileInputStream(path);

      MultipartFile file = new MockMultipartFile(
          "transaction.csv",
          "biggerFile.csv",
          "text",
          inputStream);

      return file;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

}
