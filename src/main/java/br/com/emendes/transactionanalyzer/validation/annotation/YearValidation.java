package br.com.emendes.transactionanalyzer.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = YearValidator.class)
public @interface YearValidation {
  String message() default "Invalid year";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
