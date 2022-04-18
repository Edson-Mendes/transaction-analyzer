package br.com.emendes.transactionanalyzer.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.emendes.transactionanalyzer.model.Transaction;
import br.com.emendes.transactionanalyzer.model.TransactionsImport;
import br.com.emendes.transactionanalyzer.repository.TransactionImportRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionImportService {

  private final TransactionImportRepository transactionImportRepository;

  public void save(List<Transaction> transactions) {
    LocalDate transactionsDate = transactions.get(0).getDateTime().toLocalDate();

    TransactionsImport transactionImport = TransactionsImport.builder()
        .transactions(transactions)
        .dateTime(LocalDateTime.now())
        .date(transactionsDate)
        .build();

    transactionImportRepository.save(transactionImport);
  }

  public List<TransactionsImport> read() {
    // TODO: Buscar em ordem decrescente por data
    return transactionImportRepository.findAll();
  }

}
