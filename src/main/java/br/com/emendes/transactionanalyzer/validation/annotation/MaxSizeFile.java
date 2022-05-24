package br.com.emendes.transactionanalyzer.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The annotated element must have a size less or equal than {@code size}Bytes
 * <p>
 * {@code null} elements are considered valid.
 * </p>
 * 
 * @author Edson Mendes
 */
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MaxSizeFileValidator.class)
public @interface MaxSizeFile {
  String message() default "file bigger than max size";

  long size() default 10240l;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
