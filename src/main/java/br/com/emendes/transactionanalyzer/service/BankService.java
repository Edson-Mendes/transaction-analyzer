package br.com.emendes.transactionanalyzer.service;

import org.springframework.stereotype.Service;

import br.com.emendes.transactionanalyzer.model.entity.Bank;
import br.com.emendes.transactionanalyzer.repository.BankRepository;
import br.com.emendes.transactionanalyzer.validation.exception.BankNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankService {

  private final BankRepository bankRepository;

  public boolean existsByName(String bankName) {
    return bankRepository.existsByName(bankName);
  }

  public Bank findByName(String bankName) {
    return bankRepository
        .findByName(bankName)
        .orElseThrow(() -> new BankNotFoundException("Bank not found"));
  }
}
