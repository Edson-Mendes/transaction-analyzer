package br.com.emendes.transactionanalyzer.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.emendes.transactionanalyzer.controller.form.AnalysisDateForm;

@DisplayName("Tests for MonthNumber annotation")
public class MonthNumberTests {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
  private final String validYear = "2022";

  @Test
  @DisplayName("Must not return violations when month is valid")
  void mustNotReturnViolationsWhenMonthIsValid() {
    String january = "1";
    String june = "6";
    String december = "12";

    final AnalysisDateForm analysisJan2022 = new AnalysisDateForm(january, validYear);
    final Set<ConstraintViolation<AnalysisDateForm>> constraintViolationsJan2022 = validator.validate(analysisJan2022);
    final AnalysisDateForm analysisJun2022 = new AnalysisDateForm(june, validYear);
    final Set<ConstraintViolation<AnalysisDateForm>> constraintViolationsJun2022 = validator.validate(analysisJun2022);
    final AnalysisDateForm analysisDec2022 = new AnalysisDateForm(december, validYear);
    final Set<ConstraintViolation<AnalysisDateForm>> constraintViolationsDec2022 = validator.validate(analysisDec2022);

    Assertions.assertTrue(constraintViolationsJan2022.isEmpty());
    Assertions.assertTrue(constraintViolationsJun2022.isEmpty());
    Assertions.assertTrue(constraintViolationsDec2022.isEmpty());
  }

  @Test
  @DisplayName("Return violations when month is less than 1")
  void returnViolationsWhenMonthIsLessThan1() {
    String lessThan1 = "0";

    final AnalysisDateForm analysis0_2022 = new AnalysisDateForm(lessThan1, validYear);
    final Set<ConstraintViolation<AnalysisDateForm>> constraintViolations0_2022 = validator.validate(analysis0_2022);

    Assertions.assertFalse(constraintViolations0_2022.isEmpty());
    Assertions.assertEquals(1, constraintViolations0_2022.size());
    Assertions.assertEquals("Invalid month", constraintViolations0_2022.stream().findAny().get().getMessage());
  }

  @Test
  @DisplayName("Return violations when month is bigger than 12")
  void returnViolationsWhenMonthIsBiggerThan12() {
    String lessThan1 = "13";

    final AnalysisDateForm analysis13_2022 = new AnalysisDateForm(lessThan1, validYear);
    final Set<ConstraintViolation<AnalysisDateForm>> constraintViolations13_2022 = validator.validate(analysis13_2022);

    Assertions.assertFalse(constraintViolations13_2022.isEmpty());
    Assertions.assertEquals(1, constraintViolations13_2022.size());
    Assertions.assertEquals("Invalid month", constraintViolations13_2022.stream().findAny().get().getMessage());
  }

}
