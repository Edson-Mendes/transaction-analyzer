package br.com.emendes.transactionanalyzer.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.emendes.transactionanalyzer.model.dto.SuspiciousAccountDto;
import br.com.emendes.transactionanalyzer.model.dto.TransactionDto;
import br.com.emendes.transactionanalyzer.model.entity.Transaction;
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

  public List<SuspiciousAccountDto> findSuspiciousAccounts(Integer month, Integer year) {
    final BigDecimal limit = new BigDecimal("1000000.00");

    List<SuspiciousAccountDto> suspiciousOriginAccounts = transactionRepository
        .findSuspiciousOriginAccounts(month, year)
        .stream()
        .filter(soa -> soa.getValue().compareTo(limit) >= 1)
        .toList();

    List<SuspiciousAccountDto> suspiciousDestinationAccounts = transactionRepository
        .findSuspiciousDestinationAccounts(month, year)
        .stream()
        .filter(soa -> soa.getValue().compareTo(limit) >= 1)
        .toList();
    List<SuspiciousAccountDto> suspiciousAccounts = new ArrayList<>();

    suspiciousDestinationAccounts.forEach(suspiciousAccounts::add);
    suspiciousOriginAccounts.forEach(suspiciousAccounts::add);

    // System.out.println("===================================================================");
    // suspiciousAccounts.forEach(sa -> {
    // String message = String.format("%s - %s - %s : %s - %s",
    // sa.getBankName(),
    // sa.getBranchNumber(),
    // sa.getAccountNumber(),
    // sa.getValue(),
    // sa.getType());

    // System.out.println(message);
    // });
    // System.out.println("===================================================================");

    return suspiciousAccounts;
  }

}
