package br.com.emendes.transactionanalyzer.model.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TransactionsDto {

  private final String originBank;
  private final String originBranch;
  private final String originAccount;

  private final String destinationBank;
  private final String destinationBranch;
  private final String destinationAccount;

  private final BigDecimal value;

}
