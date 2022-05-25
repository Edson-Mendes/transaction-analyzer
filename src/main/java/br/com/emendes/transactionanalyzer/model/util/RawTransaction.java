package br.com.emendes.transactionanalyzer.model.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.emendes.transactionanalyzer.validation.annotation.DateTimeValidation;
import br.com.emendes.transactionanalyzer.validation.annotation.ValueValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que recebe os dados brutos de uma transação.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
    "originBank",
    "originBranch",
    "originAccount",
    "destinationBank",
    "destinationBranch",
    "destinationAccount",
    "value",
    "dateTime" })
@Builder
public class RawTransaction {

  @NotNull
  @NotBlank
  private String originBank;
  @NotNull
  @NotBlank
  private String originBranch;
  @NotNull
  @NotBlank
  private String originAccount;

  @NotNull
  @NotBlank
  private String destinationBank;
  @NotNull
  @NotBlank
  private String destinationBranch;
  @NotNull
  @NotBlank
  private String destinationAccount;

  @NotNull
  @ValueValidation
  private String value;
  @NotNull
  @DateTimeValidation
  private String dateTime;

  public BigDecimal getValueAsBigDecimal() {
    return new BigDecimal(this.value).setScale(2, RoundingMode.DOWN);
  }

  public LocalDateTime getDateTimeAsLocalDateTime() {
    return LocalDateTime.parse(this.dateTime);
  }

  public LocalDate getDate() {
    return getDateTimeAsLocalDateTime().toLocalDate();
  }

}
