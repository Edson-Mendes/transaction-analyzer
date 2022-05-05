package br.com.emendes.transactionanalyzer.model.form;

import javax.validation.constraints.NotEmpty;

import br.com.emendes.transactionanalyzer.validation.annotation.MonthNumber;
import br.com.emendes.transactionanalyzer.validation.annotation.YearValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalysisDateForm {

  @NotEmpty
  @MonthNumber
  private String month;

  @NotEmpty
  @YearValidation
  private String year;

  public Integer getMonthAsInteger() {
    return Integer.valueOf(month);
  }

  public Integer getYearAsInteger() {
    return Integer.valueOf(year);
  }

}
