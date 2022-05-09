package br.com.emendes.transactionanalyzer.exception;

public class TransactionsDateAlreadyExistsException extends RuntimeException {

  public TransactionsDateAlreadyExistsException(String message) {
    super(message);
  }

}
