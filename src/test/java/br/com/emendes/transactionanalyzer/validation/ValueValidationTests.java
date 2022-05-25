package br.com.emendes.transactionanalyzer.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.emendes.transactionanalyzer.model.util.RawTransaction;
import br.com.emendes.transactionanalyzer.util.creator.RawTransactionCreator;

@DisplayName("Tests for annotation ValueValidation")
public class ValueValidationTests {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  @DisplayName("Must Return empty violations when value is valid")
  public void mustReturnEmptyViolationsWhenValueIsValid() {
    String value = "5000.00";
    RawTransaction validRawTransaction = RawTransactionCreator.withValue(value);

    Set<ConstraintViolation<RawTransaction>> violations = validator.validate(validRawTransaction);

    Assertions.assertThat(violations).isEmpty();
  }

  @Test
  @DisplayName("Must return violations when value can't be parsed")
  public void mustReturnViolationWhenValueCanNotBeParsed() {
    String value = "5OOO.OO";
    RawTransaction withInvalidValue = RawTransactionCreator.withValue(value);

    Set<ConstraintViolation<RawTransaction>> violations = validator.validate(withInvalidValue);

    Assertions.assertThat(violations).isNotEmpty();
    Assertions.assertThat(violations.size())
        .isEqualTo(1);
    Assertions.assertThat(violations.stream().findAny().get().getMessage())
        .isEqualTo("Invalid value");
  }

  @Test
  @DisplayName("Must return violations when value is negative")
  public void mustReturnViolationWhenDateTimeIsLessThanMinDateTime() {
    String value = "-100.00";
    RawTransaction withValueNegative = RawTransactionCreator.withValue(value);

    Set<ConstraintViolation<RawTransaction>> violations = validator.validate(withValueNegative);

    Assertions.assertThat(violations).isNotEmpty();
    Assertions.assertThat(violations.size())
        .isEqualTo(1);
    Assertions.assertThat(violations.stream().findAny().get().getMessage())
        .isEqualTo("Invalid value");
  }

  @Test
  @DisplayName("Must return violations when value is less than 0.01")
  public void mustReturnViolationWhenValueIsLessThan001() {
    String value = "0.009";
    RawTransaction withValueLessThan001 = RawTransactionCreator.withValue(value);

    Set<ConstraintViolation<RawTransaction>> violations = validator.validate(withValueLessThan001);

    Assertions.assertThat(violations).isNotEmpty();
    Assertions.assertThat(violations.size())
        .isEqualTo(1);
    Assertions.assertThat(violations.stream().findAny().get().getMessage())
        .isEqualTo("Invalid value");
  }

  @Test
  @DisplayName("Must return violations when value is bigger than 9.999.999.999.999,00.")
  public void mustReturnViolationWhenDateTimeIsBiggerThanMaxDateTime() {
    String value = "10000000000000.00";
    RawTransaction withValueBiggerThanMax = RawTransactionCreator.withValue(value);

    Set<ConstraintViolation<RawTransaction>> violations = validator.validate(withValueBiggerThanMax);

    Assertions.assertThat(violations).isNotEmpty();
    Assertions.assertThat(violations.size())
        .isEqualTo(1);
    Assertions.assertThat(violations.stream().findAny().get().getMessage())
        .isEqualTo("Invalid value");
  }

}
