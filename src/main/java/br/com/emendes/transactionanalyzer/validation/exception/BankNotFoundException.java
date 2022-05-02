package br.com.emendes.transactionanalyzer.validation.exception;

public class BankNotFoundException extends RuntimeException {
  public BankNotFoundException(String message) {
    super(message);
  }
}
