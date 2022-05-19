package br.com.emendes.transactionanalyzer.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.emendes.transactionanalyzer.model.entity.Bank;
import br.com.emendes.transactionanalyzer.util.BankCreator;

@DataJpaTest
@DisplayName("Tests for BankRepository")
public class BankRepositoryTests {

  @Autowired
  private BankRepository bankRepository;

  @Test
  @DisplayName("Must return a empty optional when find by not existing bank name")
  void mustReturnAEmptyOptionalWhenFindByNotExistingName() {
    String bankSearched = "NUBANCK";

    Optional<Bank> optionalBank = bankRepository.findByName(bankSearched);

    Assertions.assertThat(optionalBank.isPresent()).isFalse();
  }

  @Test
  @DisplayName("Must return a empty optional when find by not existing bank name")
  void mustReturnAOptionalWithABankWhenFindByExistingName() {
    // Persistir Bank antes de busca-lo
    persistBank();
    String bankSearched = "NUBANK";

    Optional<Bank> optionalBank = bankRepository.findByName(bankSearched);

    Assertions.assertThat(optionalBank.isPresent()).isTrue();
    Assertions.assertThat(optionalBank.get().getName()).isEqualTo(bankSearched);
  }

  @Test
  @DisplayName("Must return true when bank name exists")
  void mustReturnTrueWhenBankNameExists() {
    // Persistir Bank antes de verificar se existe.
    persistBank();

    String bankName = "NUBANK";

    boolean existsBank = bankRepository.existsByName(bankName);

    Assertions.assertThat(existsBank).isTrue();
  }

  @Test
  @DisplayName("Must return false when bank name not exists")
  void mustReturnFalseWhenBankNameNotExists() {
    String bankName = "NUBANCO";

    boolean existsBank = bankRepository.existsByName(bankName);

    Assertions.assertThat(existsBank).isFalse();
  }

  private Bank persistBank() {
    return bankRepository.save(BankCreator.validBank());
  }
}
