package br.com.emendes.transactionanalyzer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Type {

  SUCCESS("alert-success"),
  ERROR("alert-danger");

  private final String value;

}
