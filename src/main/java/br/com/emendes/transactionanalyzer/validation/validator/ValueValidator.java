package br.com.emendes.transactionanalyzer.validation.validator;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.emendes.transactionanalyzer.validation.annotation.ValueValidation;

public class ValueValidator implements ConstraintValidator<ValueValidation, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    try {
      BigDecimal minValue = new BigDecimal("0.01");
      BigDecimal maxValue = new BigDecimal("9999999999999.99");

      BigDecimal valueBigDecimal = new BigDecimal(value).setScale(2, RoundingMode.DOWN);
      if (valueBigDecimal.compareTo(minValue) < 0 || valueBigDecimal.compareTo(maxValue) > 0) {
        return false;
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }

}
