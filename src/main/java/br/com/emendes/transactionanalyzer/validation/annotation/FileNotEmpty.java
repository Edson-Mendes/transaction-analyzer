package br.com.emendes.transactionanalyzer.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The annotated element must not be {@code empty file}.
 * <p>
 * {@code null} elements are considered valid.
 * </p>
 * 
 * @author Edson Mendes
 */
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileNotEmptyValidator.class)
public @interface FileNotEmpty {
  String message() default "Empty file";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
