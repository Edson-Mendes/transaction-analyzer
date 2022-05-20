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

import br.com.emendes.transactionanalyzer.model.entity.Branch;
import br.com.emendes.transactionanalyzer.repository.BranchRepository;
import br.com.emendes.transactionanalyzer.util.BranchCreator;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for BranchService")
public class BranchServiceTests {

  @InjectMocks
  private BranchService branchService;

  @Mock
  private BranchRepository branchRepositoryMock;

  private final String BANK_NAME = "NUBANK";

  @BeforeEach
  void setup() {
    Branch validBranch = BranchCreator.validBranch();
    Optional<Branch> optionalBranch = Optional.of(validBranch);
    Optional<Branch> optionalEmpty = Optional.empty();

    BDDMockito.when(branchRepositoryMock.save(ArgumentMatchers.any(Branch.class)))
        .thenReturn(validBranch);
    BDDMockito.when(branchRepositoryMock.findByBranchNumberAndBankName("1234", BANK_NAME))
        .thenReturn(optionalBranch);
    BDDMockito.when(branchRepositoryMock.findByBranchNumberAndBankName("2222", BANK_NAME))
        .thenReturn(optionalEmpty);
  }

  @Test
  @DisplayName("Must return branch with id when save successful")
  void mustReturnBranchWithIdWhenSaveSuccessful() {
    Branch branchToBeSaved = BranchCreator.validBranchWithoutId();

    Branch branchSaved = branchService.save(branchToBeSaved);

    Assertions.assertThat(branchSaved).isNotNull();
    Assertions.assertThat(branchSaved.getNumber())
        .isEqualTo(branchToBeSaved.getNumber());
    Assertions.assertThat(branchSaved.getBank().getName())
        .isEqualTo(branchToBeSaved.getBank().getName());
  }

  @Test
  @DisplayName("Must return branch when match with branchNumber and bankName")
  void mustReturnAccountWhenMatchs() {
    String branchNumber = "1234";

    Branch branchFinded = branchService.findByBranchNumberAndBankName(branchNumber, BANK_NAME);

    Assertions.assertThat(branchFinded).isNotNull();
    Assertions.assertThat(branchFinded.getId()).isNotNull();
    Assertions.assertThat(branchFinded.getNumber()).isEqualTo(branchNumber);
  }

  @Test
  @DisplayName("Must return null when not find by accountNumber, branchNumber and bankName")
  void mustReturnNullWhenNotFindAccount() {
    String branchNumber = "2222";

    Branch branchFinded = branchService.findByBranchNumberAndBankName(branchNumber, BANK_NAME);

    Assertions.assertThat(branchFinded).isNull();
  }

}
