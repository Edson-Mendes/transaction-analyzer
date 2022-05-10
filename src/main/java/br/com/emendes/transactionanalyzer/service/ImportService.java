package br.com.emendes.transactionanalyzer.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.emendes.transactionanalyzer.exception.ImportNotFoundException;
import br.com.emendes.transactionanalyzer.exception.InvalidFileException;
import br.com.emendes.transactionanalyzer.exception.TransactionsDateAlreadyExistsException;
import br.com.emendes.transactionanalyzer.model.dto.ImportDetailsDto;
import br.com.emendes.transactionanalyzer.model.dto.TransactionsImportDto;
import br.com.emendes.transactionanalyzer.model.entity.Transaction;
import br.com.emendes.transactionanalyzer.model.entity.TransactionsImport;
import br.com.emendes.transactionanalyzer.model.entity.User;
import br.com.emendes.transactionanalyzer.model.util.RawTransaction;
import br.com.emendes.transactionanalyzer.repository.TransactionsImportRepository;
import br.com.emendes.transactionanalyzer.util.DateFormatter;
import br.com.emendes.transactionanalyzer.util.ReadFile;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImportService {

  private final TransactionsImportRepository transactionsImportRepository;
  private final UserService userService;
  private final TransactionService transactionService;

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

  public void processImport(MultipartFile file, String email) {
    List<String> transactionLines = ReadFile.readMultipartFile(file);

    List<RawTransaction> rawTransactions = RawTransaction.fromTransactionsLines(transactionLines);

    if (rawTransactions.isEmpty()) {
      throw new InvalidFileException("File hasn't valid transactions");
    }
    LocalDate transactionsDate = rawTransactions.get(0).getDateTime().toLocalDate();
    if (transactionsImportRepository.existsByTransactionsDate(transactionsDate)) {
      String message = String.format("Already exists a imported file for %s",
          transactionsDate.format(DateFormatter.formatter));
      throw new TransactionsDateAlreadyExistsException(message);
    }
    List<RawTransaction> filteredRawTransactions = RawTransaction.filterByDate(rawTransactions, transactionsDate);

    List<Transaction> transactions = transactionService.generateTransactionsList(filteredRawTransactions);

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
