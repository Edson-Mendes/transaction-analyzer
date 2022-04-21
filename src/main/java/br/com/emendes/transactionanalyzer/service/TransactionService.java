package br.com.emendes.transactionanalyzer.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.emendes.transactionanalyzer.model.Transaction;
import br.com.emendes.transactionanalyzer.repository.TransactionRepository;
import br.com.emendes.transactionanalyzer.util.ReadFile;
import br.com.emendes.transactionanalyzer.util.TransactionUtil;
import lombok.RequiredArgsConstructor;

// TODO: deletar se não for mais usar
@Service
@RequiredArgsConstructor
public class TransactionService {

  private final TransactionRepository transactionRepository;

  private final TransactionsImportService transactionsImportService;

  public void processFile(MultipartFile file) {
    // TODO: Devolver uma mensagem de sucesso.
    List<String> transactionsLines = ReadFile.readMultipartFile(file);
    List<Transaction> transactions = TransactionUtil.generateTransactionsList(transactionsLines);

    // transactionImportService.save(transactionRepository.saveAll(transactions));
  }

}
