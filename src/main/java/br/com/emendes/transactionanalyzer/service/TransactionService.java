package br.com.emendes.transactionanalyzer.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.emendes.transactionanalyzer.model.entity.Account;
import br.com.emendes.transactionanalyzer.model.entity.Transaction;
import br.com.emendes.transactionanalyzer.validation.TransactionLineValidation;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

  private final BankService bankService;
  private final AccountService accountService;

  /**
   * Converte uma lista de Strings que representa as transações, em uma lista de
   * transações.
   * Strings que possuem campos inválidos são descartados.
   * 
   * @param transactionsLines
   * @return Lista de transações
   */
  public List<Transaction> generateTransactionsList(List<String> transactionsLines) {
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

      String originBankName = transactionFields[0].toUpperCase();
      String originBranchNumber = transactionFields[1];
      String originAccountNumber = transactionFields[2];

      String destinationBankName = transactionFields[3].toUpperCase();
      String destinationBranchNumber = transactionFields[4];
      String destinationAccountNumber = transactionFields[5];

      if (bankService.existsByName(originBankName) && bankService.existsByName(destinationBankName)) {

        try {
          Account originAccount = accountService.createIfNotExists(
              originAccountNumber,
              originBranchNumber,
              originBankName);
          Account destinationAccount = accountService.createIfNotExists(
              destinationAccountNumber,
              destinationBranchNumber,
              destinationBankName);

          BigDecimal value = new BigDecimal(transactionFields[6]);
          LocalDateTime transactionDateTime = LocalDateTime.parse(transactionFields[7]);

          return Transaction.builder()
              .originAccount(originAccount)
              .destinationAccount(destinationAccount)
              .value(value)
              .dateTime(transactionDateTime)
              .build();
        } catch (Exception e) {
          System.out.println("Something went wrong");
        }
      }

    }
    return null;
  }

  public List<Transaction> filterTransactionByDate(List<Transaction> transactions, LocalDate transactionsDate) {
    return transactions
        .stream()
        .filter(t -> t.getDateTime().toLocalDate().equals(transactionsDate))
        .collect(Collectors.toList());

  }

}
