package br.com.emendes.transactionanalyzer.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.emendes.transactionanalyzer.validation.validator.ValueValidator;

/**
 * O elemento anotado deve ser uma {@code String} que representa um
 * {@code BigDecimal}.
 * <p>
 * O elemento é válido se estiver entre 0,01 e 9.999.999.999.999,00.
 * </p>
 * <p>
 * Dígitos abaixo de 2 casas decimais são desconsiderados ao validar ou seja,
 * 0.01234 será considerado 0.01.
 * </p>
 * {@code null} elementos são considerados válidos.
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValueValidator.class)
public @interface ValueValidation {
  String message() default "Invalid value";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
