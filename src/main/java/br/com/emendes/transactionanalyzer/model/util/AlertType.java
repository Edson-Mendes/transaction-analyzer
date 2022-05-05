package br.com.emendes.transactionanalyzer.model.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AlertType {

  SUCCESS("alert-success"),
  WARNING("alert-warning"),
  ERROR("alert-danger");

  private final String value;

}
