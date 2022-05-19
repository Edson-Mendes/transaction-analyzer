package br.com.emendes.transactionanalyzer.util;

import br.com.emendes.transactionanalyzer.model.entity.Account;

public class AccountCreator {

  public static Account validAccount() {
    return Account.builder()
        .id(1L)
        .number("005432-1")
        .branch(BranchCreator.validBranch())
        .build();
  }

  public static Account validAccountWithoutId() {
    return Account.builder()
        .number("005432-1")
        .branch(BranchCreator.validBranch())
        .build();
  }

}
