package br.com.emendes.transactionanalyzer.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.emendes.transactionanalyzer.exception.InvalidFileException;
import br.com.emendes.transactionanalyzer.model.util.RawTransaction;
import br.com.emendes.transactionanalyzer.validation.Validate;

@Service
public class RawTransactionService {

  private final Validate<RawTransaction> validateRawTransaction = new Validate<RawTransaction>();

  /**
   * Filtra a lista de rawTransactions de acordo com bean validation.
   * 
   * @param rawTransactions
   * @return {@code List<RawTransaction>} de rawTransactions válidos
   * @throws InvalidFileException se a lista a ser retornada estiver vazia.
   */
  public List<RawTransaction> filterByValidation(List<RawTransaction> rawTransactions) {
    List<RawTransaction> filteredRawTransactions = rawTransactions.stream()
        .filter(rt -> validateRawTransaction.isValid(rt))
        .collect(Collectors.toList());

    if (filteredRawTransactions.isEmpty()) {
      throw new InvalidFileException("File hasn't valid transactions");
    }
    return filteredRawTransactions;
  }

  public LocalDate getFisrtDate(List<RawTransaction> rawTransactions) {
    if (rawTransactions.isEmpty()) {
      throw new InvalidFileException("File hasn't valid transactions");
    }
    return rawTransactions.get(0).getDate();
  }

  public List<RawTransaction> filterByDate(LocalDate transactionsDate, List<RawTransaction> rawTransactions) {
    return rawTransactions
        .stream()
        .filter(rt -> LocalDateTime.parse(rt.getDateTime()).toLocalDate().equals(transactionsDate))
        .toList();
  }
}
