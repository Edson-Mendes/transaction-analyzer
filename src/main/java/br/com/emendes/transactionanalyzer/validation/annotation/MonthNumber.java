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
@Constraint(validatedBy = MonthNumberValidator.class)
public @interface MonthNumber {
  String message() default "invalid month";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String value() default "";
}

class MonthNumberValidator implements ConstraintValidator<MonthNumber, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    try {
      Integer monthNumber = Integer.valueOf(value);
      if (monthNumber >= 1 && monthNumber <= 12) {
        return true;
      }
    } catch (Exception e) {
    }
    return false;
  }

}
