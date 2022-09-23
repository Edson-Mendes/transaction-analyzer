package br.com.emendes.transactionanalyzer.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.emendes.transactionanalyzer.controller.dto.ImportDetailsDto;
import br.com.emendes.transactionanalyzer.controller.dto.TransactionsImportDto;
import br.com.emendes.transactionanalyzer.controller.form.FileForm;
import br.com.emendes.transactionanalyzer.exception.ImportNotFoundException;
import br.com.emendes.transactionanalyzer.exception.InvalidFileException;
import br.com.emendes.transactionanalyzer.exception.TransactionsDateAlreadyExistsException;
import br.com.emendes.transactionanalyzer.model.entity.Transaction;
import br.com.emendes.transactionanalyzer.model.entity.TransactionsImport;
import br.com.emendes.transactionanalyzer.model.entity.User;
import br.com.emendes.transactionanalyzer.model.util.RawTransaction;
import br.com.emendes.transactionanalyzer.repository.TransactionsImportRepository;
import br.com.emendes.transactionanalyzer.service.component.CsvTransactionReader;
import br.com.emendes.transactionanalyzer.service.component.TransactionReader;
import br.com.emendes.transactionanalyzer.util.DateFormatter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImportService {

  private final TransactionsImportRepository transactionsImportRepository;
  private final UserService userService;
  private final TransactionService transactionService;
  private final RawTransactionService rawTransactionService;

  private void save(TransactionsImport transactionsImport) {
    transactionsImportRepository.save(transactionsImport);
  }

  public List<TransactionsImportDto> findAll() {
    Sort sort = Sort.by(Direction.DESC, "transactionsDate");
    List<TransactionsImportDto> transactionsImportDto = transactionsImportRepository
        .findAll(sort)
        .stream()
        .map(t -> new TransactionsImportDto(t))
        .collect(Collectors.toList());
    return transactionsImportDto;
  }

  public ImportDetailsDto findById(Long id) {
    TransactionsImport transactionsImport = transactionsImportRepository.findById(id).orElseThrow(() -> {
      throw new ImportNotFoundException("Import not found");
    });

    return new ImportDetailsDto(transactionsImport);
  }

  public void processImport(FileForm fileForm, String email) {
    List<RawTransaction> rawTransactions = rawTransactionService.getFilteredRawTransactions(fileForm);
    LocalDate transactionsDate = rawTransactionService.getFisrtDate(rawTransactions);

    if (transactionsImportRepository.existsByTransactionsDate(transactionsDate)) {
      String message = String.format("Already exists a imported file for %s",
          transactionsDate.format(DateFormatter.formatter));
      throw new TransactionsDateAlreadyExistsException(message);
    }

    List<Transaction> transactions = transactionService.generateTransactionsList(rawTransactions);

    if (transactions.isEmpty()) {
      throw new InvalidFileException("File hasn't valid transactions");
    }

    User user = userService.findByEmail(email);

    TransactionsImport transactionsImport = TransactionsImport.builder()
        .transactionsDate(transactionsDate)
        .importDateTime(LocalDateTime.now())
        .transactions(transactions)
        .user(user)
        .build();

    save(transactionsImport);
  }

}
