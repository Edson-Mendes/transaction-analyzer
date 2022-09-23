package br.com.emendes.transactionanalyzer.service;

import br.com.emendes.transactionanalyzer.controller.form.FileForm;
import br.com.emendes.transactionanalyzer.exception.InvalidFileException;
import br.com.emendes.transactionanalyzer.model.util.RawTransaction;
import br.com.emendes.transactionanalyzer.service.component.CsvTransactionReader;
import br.com.emendes.transactionanalyzer.service.component.TransactionReader;
import br.com.emendes.transactionanalyzer.validation.Validate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RawTransactionService {

  private final Validate<RawTransaction> validateRawTransaction = new Validate<>();
  private final FileService fileService;
  public List<RawTransaction> getFilteredRawTransactions(FileForm fileForm){
    List<RawTransaction> rawTransactions = fileService
        .readFile(fileForm.getFile(), selectTransactionReader(fileForm));

    return filter(rawTransactions);
  }

  private List<RawTransaction> filter(List<RawTransaction> rawTransactions){
    List<RawTransaction> filteredRawTransactions = rawTransactions.stream()
        .filter(validateRawTransaction::isValid).toList();

    LocalDate transactionsDate = getFisrtDate(filteredRawTransactions);

    filteredRawTransactions = filteredRawTransactions.stream()
        .filter(rt -> datesMatch(rt, transactionsDate)).toList();

    return filteredRawTransactions;
  }

  public LocalDate getFisrtDate(List<RawTransaction> rawTransactions) {
    if (rawTransactions.isEmpty()) {
      throw new InvalidFileException("File hasn't valid transactions");
    }
    return rawTransactions.get(0).getDate();
  }

  private boolean datesMatch(RawTransaction rawTransaction, LocalDate date){
    return LocalDateTime.parse(rawTransaction.getDateTime()).toLocalDate().equals(date);
  }

  private TransactionReader selectTransactionReader(FileForm fileForm) {
    switch (fileForm.getFileExtension()) {
      case "csv":
        return new CsvTransactionReader();
      default:
        throw new IllegalArgumentException("Inválid file extension");
    }
  }
}
