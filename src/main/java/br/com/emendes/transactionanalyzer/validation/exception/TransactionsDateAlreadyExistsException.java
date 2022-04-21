package br.com.emendes.transactionanalyzer.validation.exception;

public class TransactionsDateAlreadyExistsException extends RuntimeException {

  public TransactionsDateAlreadyExistsException(String message) {
    super(message);
  }

}
