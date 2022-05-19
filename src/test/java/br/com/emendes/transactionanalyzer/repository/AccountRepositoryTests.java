package br.com.emendes.transactionanalyzer.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.emendes.transactionanalyzer.model.entity.Account;
import br.com.emendes.transactionanalyzer.model.entity.Bank;
import br.com.emendes.transactionanalyzer.model.entity.Branch;

@DataJpaTest
@DisplayName("Tests for AccountRepository")
class AccountRepositoryTests {

  @Autowired
  private AccountRepository accountRepository;
  @Autowired
  private BranchRepository branchRepository;
  @Autowired
  private BankRepository bankRepository;

  @Test
  @DisplayName("Create account when successful")
  void createAccountWhenSuccessful() {
    Account accountToBeSaved = createAccount();
    Account accountSaved = this.accountRepository.save(accountToBeSaved);

    Assertions.assertThat(accountSaved).isNotNull();
    Assertions.assertThat(accountSaved.getId()).isNotNull();
    Assertions.assertThat(accountSaved.getNumber()).isEqualTo(accountToBeSaved.getNumber());
  }

  @Test
  @DisplayName("Throw an exception when try save Account with non-saved branch")
  void throwAnExecptionWhenSaveAccountWithNoSavedBranch() {
    Account accountToBeSaved = createAnotherAccount();

    Assertions.assertThatExceptionOfType(DataIntegrityViolationException.class)
        .isThrownBy(() -> this.accountRepository.save(accountToBeSaved))
        .withMessageContaining("could not execute statement");
  }

  @Test
  @DisplayName("Find Account by account number, branch number and bank name when successful")
  void findAccountByAccountNumberBranchNumberBankName() {
    Account accountToBeSaved = createAccount();
    Account accountSaved = this.accountRepository.save(accountToBeSaved);

    String accountNumber = accountSaved.getNumber();
    String branchNumber = accountSaved.getBranch().getNumber();
    String bankName = accountSaved.getBranch().getBank().getName();

    Optional<Account> optionalAccountFinded = this.accountRepository
        .findByNumberAndNumberBranchAndBankName(accountNumber, branchNumber, bankName);

    Assertions.assertThat(optionalAccountFinded.isPresent()).isTrue();
    Assertions.assertThat(optionalAccountFinded.get().getId()).isEqualTo(accountSaved.getId());
  }

  @Test
  @DisplayName("Must not find account with non-exists number")
  void mustNotFindAccountWithNonExistsNumber() {
    // Salvar a agência para que o número da agência e nome do banco existam.
    Branch branchSaved = saveBranch();

    String accountNumber = "001111-1";
    String branchNumber = branchSaved.getNumber();
    String bankName = branchSaved.getBank().getName();

    Optional<Account> optionalAccountFinded = this.accountRepository
        .findByNumberAndNumberBranchAndBankName(accountNumber, branchNumber, bankName);

    Assertions.assertThat(optionalAccountFinded.isPresent()).isFalse();
  }

  // Cria uma objeto Account com Branch que não existe no DB.
  private Account createAnotherAccount() {
    Branch branch = Branch.builder()
        .id(100)
        .number("9999")
        .bank(createBank())
        .build();

    return Account.builder()
        .number("005432-1")
        .branch(branch)
        .build();
  }

  private Account createAccount() {
    return Account.builder()
        .number("005432-1")
        .branch(saveBranch())
        .build();
  }

  private Bank createBank() {
    return Bank.builder()
        .id(260)
        .name("NUBANK")
        .build();
  }

  private Branch createBranch() {
    return Branch.builder()
        .number("1234")
        .bank(saveBank())
        .build();
  }

  private Bank saveBank() {
    return bankRepository.save(createBank());
  }

  private Branch saveBranch() {
    return branchRepository.save(createBranch());
  }

}
