package br.com.emendes.transactionanalyzer.model.dto;

import java.math.BigDecimal;

import br.com.emendes.transactionanalyzer.model.entity.Account;
import br.com.emendes.transactionanalyzer.model.entity.Transaction;
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
    Account originAccount = transaction.getOriginAccount();
    Account destinationAccount = transaction.getDestinationAccount();

    this.originBank = originAccount.getBranch().getBank().getName();
    this.originBranch = originAccount.getBranch().getNumber();
    this.originAccount = originAccount.getNumber();

    this.destinationBank = destinationAccount.getBranch().getBank().getName();
    this.destinationBranch = destinationAccount.getBranch().getNumber();
    this.destinationAccount = destinationAccount.getNumber();

    this.value = transaction.getValue();
  }

}
