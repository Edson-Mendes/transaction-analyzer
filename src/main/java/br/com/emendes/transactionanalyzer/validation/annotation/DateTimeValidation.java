package br.com.emendes.transactionanalyzer.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.emendes.transactionanalyzer.validation.validator.DateTimeValidator;

/**
 * O elemento anotado deve ser uma {@code String} que representa um
 * {@code LocalDateTime}.
 * <p>
 * O LocalDateTime deve estar entre 1970-01-01T00:00:00 (incluso) e
 * 2099-12-31T23:59:59 (incluso).
 * </p>
 * <p>
 * Exemplo: 2022-05-17T17:09:35
 * </p>
 * {@code null} elementos são considerados válidos.
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateTimeValidator.class)
public @interface DateTimeValidation {
  String message() default "Invalid date time";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
