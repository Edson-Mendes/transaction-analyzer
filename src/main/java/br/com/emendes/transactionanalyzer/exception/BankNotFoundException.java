package br.com.emendes.transactionanalyzer.exception;

public class BankNotFoundException extends RuntimeException {
  public BankNotFoundException(String message) {
    super(message);
  }
}
