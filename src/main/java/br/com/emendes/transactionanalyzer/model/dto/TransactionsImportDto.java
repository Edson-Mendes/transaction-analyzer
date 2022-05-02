package br.com.emendes.transactionanalyzer.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.emendes.transactionanalyzer.model.entity.TransactionsImport;
import lombok.Getter;

@Getter
public class TransactionsImportDto {

  private final Long id;
  private final LocalDate transactionsDate;
  private final LocalDateTime importDateTime;
  private final String username;

  public TransactionsImportDto(TransactionsImport transactionsImport) {
    this.id = transactionsImport.getId();
    this.transactionsDate = transactionsImport.getTransactionsDate();
    this.importDateTime = transactionsImport.getImportDateTime();
    this.username = transactionsImport.getUser().getName();
  }
}
