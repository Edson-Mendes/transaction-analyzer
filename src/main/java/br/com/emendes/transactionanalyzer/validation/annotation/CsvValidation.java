package br.com.emendes.transactionanalyzer.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.emendes.transactionanalyzer.validation.validator.CsvValidator;

/**
 * The annotated element must be a file {@code .csv}.
 * <p>
 * {@code null} elements are considered valid.
 * </p>
 * 
 * @author Edson Mendes
 */
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CsvValidator.class)
public @interface CsvValidation {
  String message() default "Invalid file format";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
