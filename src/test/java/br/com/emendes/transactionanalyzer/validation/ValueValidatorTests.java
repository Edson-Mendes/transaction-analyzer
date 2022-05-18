package br.com.emendes.transactionanalyzer.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Tests for Value Validator")
class ValueValidatorTests {

  @Test
  @DisplayName("Return true when value is valid")
  void returnTrueWhenValueIsValid() {
    String validValue = "8000.00";
    String validMinValue = "0.01";
    String validMaxValue1 = "9999999999999.99";
    String validMaxValue2 = "9999999999999.99999";

    Assertions.assertTrue(ValueValidator.validate(validValue));
    Assertions.assertTrue(ValueValidator.validate(validMinValue));
    Assertions.assertTrue(ValueValidator.validate(validMaxValue1));
    Assertions.assertTrue(ValueValidator.validate(validMaxValue2));
  }

  @Test
  @DisplayName("Return false when value is negative")
  void returnFalseWhenValueIsNegative() {
    String negativeValue1 = "-10000.00";
    String negativeValue2 = "-0.01";
    String negativeValue3 = "-9999999999999.99";

    Assertions.assertFalse(ValueValidator.validate(negativeValue1));
    Assertions.assertFalse(ValueValidator.validate(negativeValue2));
    Assertions.assertFalse(ValueValidator.validate(negativeValue3));
  }

  @Test
  @DisplayName("Return false when value is less then 0.01")
  void returnFalseWhenValueIsLessThenMin() {
    String smallValue1 = "0.009";
    String smallValue2 = "0.009999";
    String smallValue3 = "0.0009";

    Assertions.assertFalse(ValueValidator.validate(smallValue1));
    Assertions.assertFalse(ValueValidator.validate(smallValue2));
    Assertions.assertFalse(ValueValidator.validate(smallValue3));
  }

  @Test
  @DisplayName("Return false when value is bigger then 9999999999999.99")
  void returnFalseWhenValueIsBiggerThenMax() {
    String biggerValue1 = "10000000000000.00";
    String biggerValue2 = "100000000000000.00";
    String biggerValue3 = "1000000000000000.00";
    String biggerValue4 = "10000000000000000000000.00";

    Assertions.assertFalse(ValueValidator.validate(biggerValue1));
    Assertions.assertFalse(ValueValidator.validate(biggerValue2));
    Assertions.assertFalse(ValueValidator.validate(biggerValue3));
    Assertions.assertFalse(ValueValidator.validate(biggerValue4));
  }

  @Test
  @DisplayName("Return false when value is zero")
  void returnFalseWhenValueIsZero() {
    String zero1 = "0";
    String zero2 = "0.0";
    String zero3 = "0.00";
    String zero4 = "0.000";
    String zero5 = "0000";

    Assertions.assertFalse(ValueValidator.validate(zero1));
    Assertions.assertFalse(ValueValidator.validate(zero2));
    Assertions.assertFalse(ValueValidator.validate(zero3));
    Assertions.assertFalse(ValueValidator.validate(zero4));
    Assertions.assertFalse(ValueValidator.validate(zero5));
  }

  @Test
  @DisplayName("Return false when value is not a number")
  void returnFalseWhenValueIsNotANumber() {
    String notANumber1 = "Edson";
    String notANumber2 = "100 000.00";
    String notANumber3 = "1O00.00";
    String notANumber4 = "e";
    String notANumber5 = "  1000.00  ";

    Assertions.assertFalse(ValueValidator.validate(notANumber1));
    Assertions.assertFalse(ValueValidator.validate(notANumber2));
    Assertions.assertFalse(ValueValidator.validate(notANumber3));
    Assertions.assertFalse(ValueValidator.validate(notANumber4));
    Assertions.assertFalse(ValueValidator.validate(notANumber5));
  }

}
