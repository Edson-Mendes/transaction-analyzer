package br.com.emendes.transactionanalyzer.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = YearValidator.class)
public @interface YearValidation {
  String message() default "invalid year";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}

class YearValidator implements ConstraintValidator<YearValidation, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
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
