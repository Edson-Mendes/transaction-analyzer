package br.com.emendes.transactionanalyzer.exception;

public class EmailAlreadyRegisteredException extends RuntimeException {
  public EmailAlreadyRegisteredException(String message) {
    super(message);
  }
}
