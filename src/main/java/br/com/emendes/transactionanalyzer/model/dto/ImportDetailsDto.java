package br.com.emendes.transactionanalyzer.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.emendes.transactionanalyzer.model.TransactionsImport;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ImportDetailsDto {

  private final String username;
  private final LocalDateTime importDateTime;
  private final LocalDate transactionsDate;

  public ImportDetailsDto(TransactionsImport transactionsImport) {
    this.username = transactionsImport.getUser().getName();
    this.importDateTime = transactionsImport.getImportDateTime();
    this.transactionsDate = transactionsImport.getTransactionsDate();
  }

  // private final List<TransactionsDto> transactions;

}
