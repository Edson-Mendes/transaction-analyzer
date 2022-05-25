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

@DisplayName("Tests for annotation DateTimeValidation")
public class DateTimeValidationTests {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  @DisplayName("Must Return empty violations when dateTime is valid")
  public void mustReturnEmptyViolationsWhenDateTimeIsValid() {
    String dateTime = "2022-05-21T10:39:07";
    RawTransaction validRawTransaction = RawTransactionCreator.withDateTime(dateTime);

    Set<ConstraintViolation<RawTransaction>> violations = validator.validate(validRawTransaction);

    Assertions.assertThat(violations).isEmpty();
  }

  @Test
  @DisplayName("Must return violations when dateTime can't be parsed")
  public void mustReturnViolationWhenDateTimeCanNotBeParsed() {
    String dateTime = "2022-05-21:39:07";
    RawTransaction withInvalidDateTime = RawTransactionCreator.withDateTime(dateTime);

    Set<ConstraintViolation<RawTransaction>> violations = validator.validate(withInvalidDateTime);

    Assertions.assertThat(violations).isNotEmpty();
    Assertions.assertThat(violations.size())
        .isEqualTo(1);
    Assertions.assertThat(violations.stream().findAny().get().getMessage())
        .isEqualTo("Invalid date time");
  }

  @Test
  @DisplayName("Must return violations when dateTime is less than minDateTime")
  public void mustReturnViolationWhenDateTimeIsLessThanMinDateTime() {
    String lessThanMinDateTime = "1969-05-21:39:07";
    RawTransaction withLessThanMinDateTime = RawTransactionCreator.withDateTime(lessThanMinDateTime);

    Set<ConstraintViolation<RawTransaction>> violations = validator.validate(withLessThanMinDateTime);

    Assertions.assertThat(violations).isNotEmpty();
    Assertions.assertThat(violations.size())
        .isEqualTo(1);
    Assertions.assertThat(violations.stream().findAny().get().getMessage())
        .isEqualTo("Invalid date time");
  }

  @Test
  @DisplayName("Must return violations when dateTime is bigger than maxDateTime")
  public void mustReturnViolationWhenDateTimeIsBiggerThanMaxDateTime() {
    String lessThanMinDateTime = "2100-05-21:39:07";
    RawTransaction withLessThanMinDateTime = RawTransactionCreator.withDateTime(lessThanMinDateTime);

    Set<ConstraintViolation<RawTransaction>> violations = validator.validate(withLessThanMinDateTime);

    Assertions.assertThat(violations).isNotEmpty();
    Assertions.assertThat(violations.size())
        .isEqualTo(1);
    Assertions.assertThat(violations.stream().findAny().get().getMessage())
        .isEqualTo("Invalid date time");
  }

}
