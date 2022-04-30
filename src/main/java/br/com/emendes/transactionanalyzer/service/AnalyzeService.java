package br.com.emendes.transactionanalyzer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.emendes.transactionanalyzer.model.Transaction;
import br.com.emendes.transactionanalyzer.model.dto.TransactionDto;
import br.com.emendes.transactionanalyzer.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnalyzeService {

  private final TransactionRepository transactionRepository;

  public List<TransactionDto> findSuspiciousTransactions(Integer month, Integer year) {
    List<Transaction> transactions = transactionRepository.findByMonthAndYearBiggerOrEquals(month, year);

    List<TransactionDto> transactionsDto = transactions.stream().map(t -> new TransactionDto(t)).toList();

    return transactionsDto;
  }

}
