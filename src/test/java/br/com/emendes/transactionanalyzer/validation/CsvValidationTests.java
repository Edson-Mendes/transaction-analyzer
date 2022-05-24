package br.com.emendes.transactionanalyzer.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.emendes.transactionanalyzer.controller.form.FileForm;
import br.com.emendes.transactionanalyzer.util.MultipartFileCreator;

@DisplayName("Tests for CsvValidation annotation")
public class CsvValidationTests {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  @DisplayName("Must not return violations when file is .csv")
  void mustNotReturnViolationWhenFileIsCSV() {
    final FileForm fileForm = new FileForm(MultipartFileCreator.validFile());

    final Set<ConstraintViolation<FileForm>> constraintViolations = validator.validate(fileForm);

    Assertions.assertThat(constraintViolations.isEmpty())
        .isTrue();
  }

  @Test
  @DisplayName("Must return violations when file isn't .csv")
  void mustReturnViolationWhenFileIsCSV() {
    final FileForm fileForm = new FileForm(MultipartFileCreator.invalidFileType());

    final Set<ConstraintViolation<FileForm>> constraintViolations = validator.validate(fileForm);

    Assertions.assertThat(constraintViolations.isEmpty())
        .isFalse();
    Assertions.assertThat(constraintViolations.size())
        .isEqualTo(1);
    Assertions.assertThat(constraintViolations.stream().findAny().get().getMessage())
        .isEqualTo("Invalid file format");
  }

}
