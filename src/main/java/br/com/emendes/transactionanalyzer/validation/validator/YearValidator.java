package br.com.emendes.transactionanalyzer.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.emendes.transactionanalyzer.validation.annotation.YearValidation;

public class YearValidator implements ConstraintValidator<YearValidation, String> {
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    if (value.isEmpty()) {
      return true;
    }
    try {
      Integer.valueOf(value);
      if (value.length() == 4) {
        return true;
      }
    } catch (Exception e) {
      return false;
    }
    return false;
  }
}
