package br.com.emendes.transactionanalyzer.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.emendes.transactionanalyzer.model.Transaction;
import br.com.emendes.transactionanalyzer.model.TransactionsImport;
import br.com.emendes.transactionanalyzer.repository.TransactionsImportRepository;
import br.com.emendes.transactionanalyzer.util.DateFormatter;
import br.com.emendes.transactionanalyzer.util.ReadFile;
import br.com.emendes.transactionanalyzer.util.TransactionUtil;
import br.com.emendes.transactionanalyzer.validation.exception.TransactionsDateAlreadyExistsException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionsImportService {

  private final TransactionsImportRepository transactionsImportRepository;

  private void save(TransactionsImport transactionsImport) {
    transactionsImportRepository.save(transactionsImport);
  }

  public List<TransactionsImport> findAll() {
    Sort sort = Sort.by(Direction.DESC, "transactionsDate");
    return transactionsImportRepository.findAll(sort);
  }

  public void processImport(MultipartFile file) {
    // TODO: Devolver uma mensagem de sucesso.
    List<String> transactionsLines = ReadFile.readMultipartFile(file);
    List<Transaction> transactions = TransactionUtil.generateTransactionsList(transactionsLines);

    // Verificar se já existe um import no banco de dados com essa data.
    LocalDate transactionsDate = transactions.get(0).getDateTime().toLocalDate();
    if (transactionsImportRepository.existsByTransactionsDate(transactionsDate)) {
      String message = String.format("Já existe transações do dia %s",
          transactionsDate.format(DateFormatter.formatter));
      throw new TransactionsDateAlreadyExistsException(message);
    }

    List<Transaction> filteredTransactions = TransactionUtil.filterTransactionByDate(transactions, transactionsDate);

    TransactionsImport transactionsImport = TransactionsImport.builder()
        .transactionsDate(transactionsDate)
        .importDateTime(LocalDateTime.now())
        .transactions(filteredTransactions)
        .build();

    save(transactionsImport);
  }
}
