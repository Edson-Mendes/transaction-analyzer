package br.com.emendes.transactionanalyzer.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.emendes.transactionanalyzer.model.Transaction;
import br.com.emendes.transactionanalyzer.validation.TransactionLineValidation;

public abstract class TransactionUtil {

  public static List<Transaction> generateTransactionsList(List<String> transactionsLines) {
    List<Transaction> transactions = new ArrayList<>();
    // FIXME: Se todas as transactionsLines forem inválidas, transactionsDate
    // receberá nulo.
    LocalDate transactionsDate = getTransactionsDate(transactionsLines);

    transactionsLines.forEach(transactionLine -> {
      Transaction transaction = generateTransaction(transactionLine, transactionsDate);
      if (transaction != null) {
        transactions.add(transaction);
      }
    });

    return transactions;
  }

  private static Transaction generateTransaction(String transactionLine, LocalDate transactionsDate) {
    if (TransactionLineValidation.isValid(transactionLine)) {
      String[] transactionFields = transactionLine.split(",");
      LocalDateTime transactionDateTime = LocalDateTime.parse(transactionFields[7]);

      if (transactionDateTime.toLocalDate().equals(transactionsDate)) {
        return Transaction.builder()
            .originBank(transactionFields[0])
            .originBranch(transactionFields[1])
            .originAccount(transactionFields[2])
            .destinationBank(transactionFields[3])
            .destinationBranch(transactionFields[4])
            .destinationAccount(transactionFields[5])
            .value(new BigDecimal(transactionFields[6]))
            .dateTime(transactionDateTime)
            .build();
      }
    }
    return null;
  }

  private static LocalDate getTransactionsDate(List<String> transactionsLines) {
    for (String transactionLine : transactionsLines) {
      if (TransactionLineValidation.isValid(transactionLine)) {
        String[] transactionFields = transactionLine.split(",");
        return LocalDateTime.parse(transactionFields[7]).toLocalDate();
      }
    }
    return null;
  }
}
