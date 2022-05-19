package br.com.emendes.transactionanalyzer.util;

import br.com.emendes.transactionanalyzer.model.entity.Bank;

public class BankCreator {

  public static Bank validBank() {
    return Bank.builder()
        .id(260)
        .name("NUBANK")
        .build();
  }

}
