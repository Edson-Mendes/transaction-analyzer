package br.com.emendes.transactionanalyzer.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.emendes.transactionanalyzer.exception.BankNotFoundException;
import br.com.emendes.transactionanalyzer.model.entity.Bank;
import br.com.emendes.transactionanalyzer.repository.BankRepository;
import br.com.emendes.transactionanalyzer.util.BankCreator;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for BankService")
public class BankServiceTests {

  @InjectMocks
  private BankService bankService;

  @Mock
  private BankRepository bankRepositoryMock;

  @BeforeEach
  void setup() {
    Optional<Bank> optionalBank = Optional.of(BankCreator.validBank());
    Optional<Bank> optionalEmpty = Optional.empty();

    BDDMockito.when(bankRepositoryMock.findByName("NUBANK"))
        .thenReturn(optionalBank);

    BDDMockito.when(bankRepositoryMock.findByName("BANCO NU"))
        .thenReturn(optionalEmpty);

    BDDMockito.when(bankRepositoryMock.existsByName("NUBANK"))
        .thenReturn(true);
  }

  @Test
  @DisplayName("Must return true when find bank by name")
  void mustReturnTrueIfBankExistsOnDB() {
    String bankName = "NUBANK";

    boolean existsBank = bankService.existsByName(bankName);

    Assertions.assertThat(existsBank).isTrue();
  }

  @Test
  @DisplayName("Must return a bank when find by existing bank name")
  void mustReturnABankWhenFindByExistingName() {
    String bankName = "NUBANK";

    Bank bankFinded = bankService.findByName(bankName);

    Assertions.assertThat(bankFinded).isNotNull();
    Assertions.assertThat(bankFinded.getName()).isEqualTo(bankName);
  }

  @Test
  @DisplayName("Must throw an exception when find by not existing bank name")
  void mustThrowAnExceptionWhenFindByNotExistingName() {
    String bankName = "BANCO NU";

    Assertions.assertThatExceptionOfType(BankNotFoundException.class)
        .isThrownBy(() -> bankService.findByName(bankName))
        .withMessage("Bank not found");
  }

}
