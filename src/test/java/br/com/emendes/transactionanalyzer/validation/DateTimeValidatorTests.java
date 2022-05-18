package br.com.emendes.transactionanalyzer.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Tests for DateTime Validator")
public class DateTimeValidatorTests {

  @Test
  @DisplayName("Return true when dateTime is valid")
  public void returnTrueWhenDateTimeIsValid() {
    String validDateTime1 = "2021-12-31T23:59:59";
    String validDateTime2 = "2022-01-01T00:00:00";
    String validDateTime3 = "1970-01-01T00:00:00";
    String validDateTime4 = "2099-12-31T23:59:59";

    Assertions.assertTrue(DateTimeValidator.validate(validDateTime1));
    Assertions.assertTrue(DateTimeValidator.validate(validDateTime2));
    Assertions.assertTrue(DateTimeValidator.validate(validDateTime3));
    Assertions.assertTrue(DateTimeValidator.validate(validDateTime4));
  }

  @Test
  @DisplayName("Return false when dateTime can't be parsed")
  public void returnFalseWhenDateTimeIsInvalid() {
    String invalidDateTime1 = "2O22-05-15T1O:35:21";
    String invalidDateTime2 = "2021-12-31 23:59:59";
    String invalidDateTime3 = "2021-12-31 T23:59:59";
    String invalidDateTime4 = "2021-12-31T 23:59:59";
    String invalidDateTime5 = "2022-01-01T24:00:00";
    String invalidDateTime6 = "2022-01-01T00:60:00";

    Assertions.assertFalse(DateTimeValidator.validate(invalidDateTime1));
    Assertions.assertFalse(DateTimeValidator.validate(invalidDateTime2));
    Assertions.assertFalse(DateTimeValidator.validate(invalidDateTime3));
    Assertions.assertFalse(DateTimeValidator.validate(invalidDateTime4));
    Assertions.assertFalse(DateTimeValidator.validate(invalidDateTime5));
    Assertions.assertFalse(DateTimeValidator.validate(invalidDateTime6));
  }

  @Test
  @DisplayName("Return false when dateTime is before 1970-01-01T00:00:00")
  public void returnFalseWhenDateTimeIsBeforeThanMinDateTime() {
    String beforeMinDateTime1 = "1969-12-31T23:59:59";
    String beforeMinDateTime2 = "1900-10-03T10:19:12";
    String beforeMinDateTime3 = "1000-10-03T10:19:12";

    Assertions.assertFalse(DateTimeValidator.validate(beforeMinDateTime1));
    Assertions.assertFalse(DateTimeValidator.validate(beforeMinDateTime2));
    Assertions.assertFalse(DateTimeValidator.validate(beforeMinDateTime3));
  }

  @Test
  @DisplayName("Return false when dateTime is after than 2099-12-31T23:59:59")
  public void returnFalseWhenDateTimeIsAfterThanMaxDateTime() {
    String afterMaxDateTime1 = "2100-01-01T00:00:00";
    String afterMaxDateTime2 = "2222-11-22T22:22:22";
    String afterMaxDateTime3 = "9999-09-09T09:09:09";

    Assertions.assertFalse(DateTimeValidator.validate(afterMaxDateTime1));
    Assertions.assertFalse(DateTimeValidator.validate(afterMaxDateTime2));
    Assertions.assertFalse(DateTimeValidator.validate(afterMaxDateTime3));
  }

}
