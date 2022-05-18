package br.com.emendes.transactionanalyzer.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.emendes.transactionanalyzer.model.form.AnalysisDateForm;

@DisplayName("Tests for YearValidation annotation")
public class YearValidationTests {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
  private final String validMonth = "6";

  @Test
  @DisplayName("Must not return violations when year is valid")
  void mustNotReturnViolationsWhenYearIsValid() {
    String year1000 = "1000";
    String year2022 = "2022";
    String year9999 = "9999";

    final AnalysisDateForm analysisYear1000 = new AnalysisDateForm(validMonth, year1000);
    final AnalysisDateForm analysisYear2022 = new AnalysisDateForm(validMonth, year2022);
    final AnalysisDateForm analysisYear9999 = new AnalysisDateForm(validMonth, year9999);

    final Set<ConstraintViolation<AnalysisDateForm>> constraintViolations1000 = validator.validate(analysisYear1000);
    final Set<ConstraintViolation<AnalysisDateForm>> constraintViolations2022 = validator.validate(analysisYear2022);
    final Set<ConstraintViolation<AnalysisDateForm>> constraintViolations9999 = validator.validate(analysisYear9999);

    Assertions.assertTrue(constraintViolations1000.isEmpty());
    Assertions.assertTrue(constraintViolations2022.isEmpty());
    Assertions.assertTrue(constraintViolations9999.isEmpty());
  }

  @Test
  @DisplayName("Return violations when year has less than 4 digits")
  void returnViolationsWhenYearHasLessThan4Digits() {
    String lessThan4Digits = "999";
    final AnalysisDateForm analysisYear999 = new AnalysisDateForm(validMonth, lessThan4Digits);
    final Set<ConstraintViolation<AnalysisDateForm>> constraintViolations999 = validator.validate(analysisYear999);

    Assertions.assertFalse(constraintViolations999.isEmpty());
    Assertions.assertEquals(1, constraintViolations999.size());
    Assertions.assertEquals("invalid year", constraintViolations999.stream().findAny().get().getMessage());
  }

  @Test
  @DisplayName("Return violations when year has more than 4 digits")
  void returnViolationsWhenYearHasMoreThan4Digits() {
    String moreThan4Digits = "10000";
    final AnalysisDateForm analysisYear10000 = new AnalysisDateForm(validMonth, moreThan4Digits);
    final Set<ConstraintViolation<AnalysisDateForm>> constraintViolations10000 = validator.validate(analysisYear10000);

    Assertions.assertFalse(constraintViolations10000.isEmpty());
    Assertions.assertEquals(1, constraintViolations10000.size());
    Assertions.assertEquals("invalid year", constraintViolations10000.stream().findAny().get().getMessage());
  }

}
