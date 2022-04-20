package br.com.emendes.transactionanalyzer.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.emendes.transactionanalyzer.model.Transaction;
import br.com.emendes.transactionanalyzer.repository.TransactionRepository;
import br.com.emendes.transactionanalyzer.util.ReadFile;
import br.com.emendes.transactionanalyzer.util.TransactionUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

  private final TransactionRepository transactionRepository;

  private final TransactionImportService transactionImportService;

  public void saveAll(MultipartFile file) {

    List<String> transactionsLines = ReadFile.readMultipartFile(file);
    List<Transaction> transactions = TransactionUtil.generateTransactionsList(transactionsLines);

    transactionImportService.save(transactionRepository.saveAll(transactions));
  }

}
