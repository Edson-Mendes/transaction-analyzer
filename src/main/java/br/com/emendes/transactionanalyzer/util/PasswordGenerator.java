package br.com.emendes.transactionanalyzer.util;

import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public abstract class PasswordGenerator {

  public static String generate() {
    // RandomString generator = new RandomString(6);
    Random generator = new Random();
    String password = String.format("%06d", generator.nextInt(1000000));
    System.out.println("=============================================");
    System.out.println("password: " + password);
    System.out.println("=============================================");
    return encrypt(password);
  }

  private static String encrypt(String password) {
    String passwordEncrypted = new BCryptPasswordEncoder().encode(password);
    return passwordEncrypted;
  }

}
