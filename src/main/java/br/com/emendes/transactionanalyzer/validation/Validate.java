package br.com.emendes.transactionanalyzer.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

public class Validate<E> {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  public boolean isValid(E arg) {
    Set<ConstraintViolation<E>> violations = validator.validate(arg);

    return violations.isEmpty();
  }

}
