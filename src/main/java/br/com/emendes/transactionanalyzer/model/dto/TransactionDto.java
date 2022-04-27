package br.com.emendes.transactionanalyzer.model.dto;

import java.math.BigDecimal;

import br.com.emendes.transactionanalyzer.model.Transaction;
import lombok.Getter;

@Getter
public class TransactionDto {

  private final String originBank;
  private final String originBranch;
  private final String originAccount;

  private final String destinationBank;
  private final String destinationBranch;
  private final String destinationAccount;

  private final BigDecimal value;

  public TransactionDto(Transaction transaction) {
    this.originBank = transaction.getOriginBank();
    this.originBranch = transaction.getOriginBranch();
    this.originAccount = transaction.getOriginAccount();
    this.destinationBank = transaction.getDestinationBank();
    this.destinationBranch = transaction.getDestinationBranch();
    this.destinationAccount = transaction.getDestinationAccount();
    this.value = transaction.getValue();
  }

}
