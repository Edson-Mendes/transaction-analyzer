package br.com.emendes.transactionanalyzer.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.emendes.transactionanalyzer.model.util.RawTransaction;
import br.com.emendes.transactionanalyzer.util.Validate;
import br.com.emendes.transactionanalyzer.util.creator.RawTransactionCreator;

@DisplayName("Tests for Validate<E>")
public class ValidateTest {

  private final Validate<RawTransaction> validateRawTransaction = new Validate<RawTransaction>();

  @Test
  @DisplayName("Must return true when rawTransaction is valid")
  void mustReturnTrueWhenRawTransactionIsValid() {
    RawTransaction validRawTransaction = RawTransactionCreator.validRawTransaction();

    boolean isValid = validateRawTransaction.isValid(validRawTransaction);

    Assertions.assertThat(isValid).isTrue();
  }

  @Test
  @DisplayName("Must return false when rawTransaction contains null field")
  void mustReturnFalseWhenRawTransactionContainsNullField() {
    RawTransaction validRawTransaction = RawTransactionCreator.rawTransactionWithNullField();

    boolean isValid = validateRawTransaction.isValid(validRawTransaction);

    Assertions.assertThat(isValid).isFalse();
  }

  @Test
  @DisplayName("Must return false when rawTransaction contains blank field")
  void mustReturnFalseWhenRawTransactionContainsBlankField() {
    RawTransaction validRawTransaction = RawTransactionCreator.rawTransactionWithBlankField();

    boolean isValid = validateRawTransaction.isValid(validRawTransaction);

    Assertions.assertThat(isValid).isFalse();
  }

}
