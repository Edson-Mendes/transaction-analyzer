package br.com.emendes.transactionanalyzer.util;

import java.util.Random;

public abstract class PasswordGenerator {

  public static String generate() {
    // RandomString generator = new RandomString(6);
    Random generator = new Random();
    String password = String.format("%06d", generator.nextInt(1000000));

    return encrypt(password);
  }

  private static String encrypt(String password) {
    String passwordEncrypted = password;
    // TODO: Usar o bcrypt para criptografar a senha.
    // String passwordEncrypted = new BCryptPasswordEncoder().encode(password);
    return passwordEncrypted;
  }

}
