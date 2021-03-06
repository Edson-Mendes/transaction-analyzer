package br.com.emendes.transactionanalyzer.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.emendes.transactionanalyzer.validation.annotation.MonthNumber;

public class MonthNumberValidator implements ConstraintValidator<MonthNumber, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    try {
      if (value == null) {
        return true;
      }
      if (value.isEmpty()) {
        return true;
      }
      Integer monthNumber = Integer.valueOf(value);
      if (monthNumber >= 1 && monthNumber <= 12) {
        return true;
      }
    } catch (Exception e) {
    }
    return false;
  }
}
