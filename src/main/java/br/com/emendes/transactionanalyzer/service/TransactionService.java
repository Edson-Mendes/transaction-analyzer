package br.com.emendes.transactionanalyzer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.emendes.transactionanalyzer.model.entity.Account;
import br.com.emendes.transactionanalyzer.model.entity.Transaction;
import br.com.emendes.transactionanalyzer.model.util.RawTransaction;
import br.com.emendes.transactionanalyzer.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

  private final BankService bankService;
  private final AccountService accountService;
  private final TransactionRepository transactionRepository;

  /**
   * Converte uma lista de Strings que representa as transações, em uma lista de
   * transações.
   * Strings que possuem campos inválidos são descartados.
   * 
   * @param transactionsLines
   * @return Lista de transações
   */
  public List<Transaction> generateTransactionsList(List<RawTransaction> rawTransactions) {
    List<Transaction> transactions = new ArrayList<>();

    rawTransactions.forEach(rt -> {
      Transaction transaction = generateTransaction(rt);
      if (transaction != null) {
        transactions.add(transaction);
      }
    });

    return transactions;
  }

  private Transaction generateTransaction(RawTransaction rawTransaction) {

    if (bankService.existsByName(rawTransaction.getOriginBank()) &&
        bankService.existsByName(rawTransaction.getDestinationBank())) {

      try {
        Account originAccount = accountService.createIfNotExists(
            rawTransaction.getOriginAccount(),
            rawTransaction.getOriginBranch(),
            rawTransaction.getOriginBank());
        Account destinationAccount = accountService.createIfNotExists(
            rawTransaction.getDestinationAccount(),
            rawTransaction.getDestinationBranch(),
            rawTransaction.getDestinationBank());

        return Transaction.builder()
            .originAccount(originAccount)
            .destinationAccount(destinationAccount)
            .value(rawTransaction.getValue())
            .dateTime(rawTransaction.getDateTime())
            .build();
      } catch (Exception e) {
        System.out.println("Something went wrong");
      }
    }

    return null;
  }

  public boolean existsByMonthAndYear(Integer month, Integer year) {
    return transactionRepository.existsByMonthAndYear(month, year);
  }

}
