package br.com.emendes.transactionanalyzer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Type {

  SUCCESS("sucesso"),
  ERROR("erro");

  private final String value;

}
