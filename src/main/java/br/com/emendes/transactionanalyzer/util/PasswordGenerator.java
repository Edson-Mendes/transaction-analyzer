package br.com.emendes.transactionanalyzer.util;

import java.util.Random;

public abstract class PasswordGenerator {

  public static String generate() {
    // RandomString generator = new RandomString(6);
    Random generator = new Random();
    String password = String.format("%06d", generator.nextInt(1000000));
    System.out.println("=============================================");
    System.out.println("password: " + password);
    System.out.println("=============================================");
    return password;
  }

}
