package br.com.emendes.transactionanalyzer.util;

import br.com.emendes.transactionanalyzer.model.entity.Branch;

public class BranchCreator {

  public static Branch validBranch() {
    return Branch.builder()
        .id(100)
        .number("1234")
        .bank(BankCreator.validBank())
        .build();
  }

  public static Branch validBranchWithoutId() {
    return Branch.builder()
        .number("1234")
        .bank(BankCreator.validBank())
        .build();
  }

}
