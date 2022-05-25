package br.com.emendes.transactionanalyzer.validation.validator;

import java.time.LocalDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.emendes.transactionanalyzer.validation.annotation.DateTimeValidation;

public class DateTimeValidator implements ConstraintValidator<DateTimeValidation, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    try {
      LocalDateTime localDateTime = LocalDateTime.parse(value);

      LocalDateTime minDateTime = LocalDateTime.of(1970, 01, 01, 00, 00, 00);
      LocalDateTime maxDateTime = LocalDateTime.of(2099, 12, 31, 23, 59, 59);
      if (localDateTime.compareTo(minDateTime) < 0 || localDateTime.compareTo(maxDateTime) > 0) {
        return false;
      }
      return true;
    } catch (Exception ex) {
      return false;
    }
  }

}
