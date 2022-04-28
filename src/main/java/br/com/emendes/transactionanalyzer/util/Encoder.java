package br.com.emendes.transactionanalyzer.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encoder {
  public static String encrypt(String password) {
    String passwordEncrypted = new BCryptPasswordEncoder().encode(password);
    return passwordEncrypted;
  }
}
