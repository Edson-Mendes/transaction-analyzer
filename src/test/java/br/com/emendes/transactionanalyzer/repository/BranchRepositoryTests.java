package br.com.emendes.transactionanalyzer.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.emendes.transactionanalyzer.model.entity.Bank;
import br.com.emendes.transactionanalyzer.model.entity.Branch;
import br.com.emendes.transactionanalyzer.util.BankCreator;
import br.com.emendes.transactionanalyzer.util.BranchCreator;

@DataJpaTest
@DisplayName("Tests for BranchRepository")
public class BranchRepositoryTests {

  @Autowired
  private BranchRepository branchRepository;

  @Autowired
  private BankRepository bankRepository;

  @Test
  @DisplayName("Must return empty optional when find by not existing branch number")
  void mustReturnEmptyOptionalWhenFindByNotExistingBranchNumber() {
    // Persistir Bank para que apenas branch number não exista
    persistBank();

    String branchNumber = "9999";
    String existingBankName = "NUBANK";

    Optional<Branch> optionalBranch = branchRepository.findByBranchNumberAndBankName(branchNumber, existingBankName);

    Assertions.assertThat(optionalBranch).isEmpty();
  }

  @Test
  @DisplayName("Must return optional branch when find by existing branch number")
  void mustReturnOptionalBranchWhenFindByExistingBranchNumber() {
    // Persistir Bank e Branch antes de fazer a busca
    persistBank();
    persistBranch();

    String branchNumber = "1234";
    String existingBankName = "NUBANK";

    Optional<Branch> optionalBranch = branchRepository.findByBranchNumberAndBankName(branchNumber, existingBankName);

    Assertions.assertThat(optionalBranch).isNotEmpty();
    Assertions.assertThat(optionalBranch.get().getNumber()).isEqualTo(branchNumber);
    Assertions.assertThat(optionalBranch.get().getBank().getName()).isEqualTo(existingBankName);
  }

  private Bank persistBank() {
    return bankRepository.save(BankCreator.validBank());
  }

  private Branch persistBranch() {
    return branchRepository.save(BranchCreator.validBranch());
  }

}
