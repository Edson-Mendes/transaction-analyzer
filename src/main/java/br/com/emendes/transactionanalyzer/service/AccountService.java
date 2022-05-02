package br.com.emendes.transactionanalyzer.service;

import org.springframework.stereotype.Service;

import br.com.emendes.transactionanalyzer.model.entity.Account;
import br.com.emendes.transactionanalyzer.model.entity.Branch;
import br.com.emendes.transactionanalyzer.repository.AccountRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {

  private final AccountRepository accountRepository;
  private final BranchService branchService;

  public Account createIfNotExists(String accountNumber, String branchNumber, String bankName) {
    Account account = findBy(accountNumber, branchNumber, bankName);
    if (account == null) {
      Branch branch = branchService.createIfNotExists(branchNumber, bankName);

      account = save(Account.builder()
          .number(accountNumber)
          .branch(branch)
          .build());
    }

    return account;
  }

  private Account save(Account account) {
    return accountRepository.save(account);
  }

  private Account findBy(String accountNumber, String branchNumber, String bankName) {
    return accountRepository
        .findByNumberAndNumberBranchAndBankName(accountNumber, branchNumber, bankName)
        .orElse(null);
  }

}
