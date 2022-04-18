package br.com.emendes.transactionanalyzer.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.emendes.transactionanalyzer.model.Transaction;
import br.com.emendes.transactionanalyzer.repository.TransactionRepository;
import br.com.emendes.transactionanalyzer.util.ReadFile;
import br.com.emendes.transactionanalyzer.validation.TransactionLineValidation;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

  private final TransactionRepository transactionRepository;

  private final TransactionImportService transactionImportService;

  public void saveAll(MultipartFile file) {

    List<String> transactionsLines = ReadFile.readMultipartFile(file);
    List<Transaction> transactions = generateTransactionsList(transactionsLines);

    transactionImportService.save(transactionRepository.saveAll(transactions));
  }

  private List<Transaction> generateTransactionsList(List<String> transactionsLines) {
    List<Transaction> transactions = new ArrayList<>();

    transactionsLines.forEach(transactionLine -> {
      Transaction transaction = generateTransaction(transactionLine);
      if (transaction != null) {
        transactions.add(transaction);
      }
    });

    return transactions;
  }

  private Transaction generateTransaction(String transactionLine) {
    if (TransactionLineValidation.isValid(transactionLine)) {
      String[] transactionFields = transactionLine.split(",");

      return Transaction.builder()
          .originBank(transactionFields[0])
          .originBranch(transactionFields[1])
          .originAccount(transactionFields[2])
          .destinationBank(transactionFields[3])
          .destinationBranch(transactionFields[4])
          .destinationAccount(transactionFields[5])
          .value(new BigDecimal(transactionFields[6]))
          .dateTime(LocalDateTime.parse(transactionFields[7]))
          .build();
    }
    return null;
  }

  private LocalDate getDateTransactions(List<String[]> transactionsArray) {
    // TODO: Deletar se não for usado
    LocalDate dateTransactions;
    transactionsArray.forEach(transactionArray -> {

    });
    return null;
  }

}
