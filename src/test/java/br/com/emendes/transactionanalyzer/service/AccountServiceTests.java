package br.com.emendes.transactionanalyzer.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.emendes.transactionanalyzer.model.entity.Account;
import br.com.emendes.transactionanalyzer.repository.AccountRepository;
import br.com.emendes.transactionanalyzer.util.AccountCreator;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for AccountService")
public class AccountServiceTests {
  @InjectMocks
  private AccountService accountService;

  @Mock
  private AccountRepository accountRepositoryMock;

  @BeforeEach
  void setup() {
    Account accountSaved = AccountCreator.validAccount();
    Optional<Account> optionalNotEmpty = Optional.of(accountSaved);
    Optional<Account> optionalEmpty = Optional.empty();

    BDDMockito.when(accountRepositoryMock.save(ArgumentMatchers.any(Account.class)))
        .thenReturn(accountSaved);

    BDDMockito.when(accountRepositoryMock
        .findByNumberAndNumberBranchAndBankName("005432-1", "1234", "NUBANK"))
        .thenReturn(optionalNotEmpty);

    BDDMockito.when(accountRepositoryMock
        .findByNumberAndNumberBranchAndBankName("000001-1", "1234", "NUBANK"))
        .thenReturn(optionalEmpty);
  }

  @Test
  @DisplayName("Must return account with id when save successful")
  void mustReturnAccountWithIdWhenSaveAccountSuccessfully() {
    Account accountToBeSaved = AccountCreator.validAccountWithoutId();

    Account accountSaved = accountService.save(accountToBeSaved);

    Assertions.assertThat(accountSaved).isNotNull();
    Assertions.assertThat(accountSaved.getId()).isNotNull();
    Assertions.assertThat(accountSaved.getNumber()).isEqualTo(accountToBeSaved.getNumber());

  }

  @Test
  @DisplayName("Must return account when match with accountNumber, branchNumber and bankName")
  void mustReturnAccountWhenMatchs() {
    String accountNumber = "005432-1";
    String branchNumber = "1234";
    String bankName = "NUBANK";

    Account accountFinded = accountService.findBy(accountNumber, branchNumber, bankName);

    Assertions.assertThat(accountFinded).isNotNull();
    Assertions.assertThat(accountFinded.getId()).isNotNull();
    Assertions.assertThat(accountFinded.getNumber()).isEqualTo(accountNumber);
  }

  @Test
  @DisplayName("Must return null when not find by accountNumber, branchNumber and bankName")
  void mustReturnNullWhenNotFindAccount() {
    String accountNumber = "000001-1";
    String branchNumber = "1234";
    String bankName = "NUBANK";

    Account accountFinded = accountService.findBy(accountNumber, branchNumber, bankName);

    Assertions.assertThat(accountFinded).isNull();
  }

}
