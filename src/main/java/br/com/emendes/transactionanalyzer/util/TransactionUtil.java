package br.com.emendes.transactionanalyzer.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.emendes.transactionanalyzer.model.Transaction;
import br.com.emendes.transactionanalyzer.validation.TransactionLineValidation;

public abstract class TransactionUtil {

  /**
   * Converte uma lista de Strings que representa as transações, em uma lista de
   * transações.
   * Strings que possuem campos inválidos são descartados.
   * 
   * @param transactionsLines
   * @return Lista de transações
   */
  public static List<Transaction> generateTransactionsList(List<String> transactionsLines) {
    List<Transaction> transactions = new ArrayList<>();

    transactionsLines.forEach(transactionLine -> {
      Transaction transaction = generateTransaction(transactionLine);
      if (transaction != null) {
        transactions.add(transaction);
      }
    });

    return transactions;
  }

  private static Transaction generateTransaction(String transactionLine) {
    if (TransactionLineValidation.isValid(transactionLine)) {
      String[] transactionFields = transactionLine.split(",");

      String originBank = transactionFields[0];
      String originBranch = transactionFields[1];
      String originAccount = transactionFields[2];
      String destinationBank = transactionFields[3];
      String destinationBranch = transactionFields[4];
      String destinationAccount = transactionFields[5];
      BigDecimal value = new BigDecimal(transactionFields[6]);
      LocalDateTime transactionDateTime = LocalDateTime.parse(transactionFields[7]);

      return Transaction.builder()
          .originBank(originBank)
          .originBranch(originBranch)
          .originAccount(originAccount)
          .destinationBank(destinationBank)
          .destinationBranch(destinationBranch)
          .destinationAccount(destinationAccount)
          .value(value)
          .dateTime(transactionDateTime)
          .build();

    }
    return null;
  }

  public static List<Transaction> filterTransactionByDate(List<Transaction> transactions, LocalDate transactionsDate) {
    return transactions
        .stream()
        .filter(t -> t.getDateTime().toLocalDate().equals(transactionsDate))
        .collect(Collectors.toList());

  }

}
