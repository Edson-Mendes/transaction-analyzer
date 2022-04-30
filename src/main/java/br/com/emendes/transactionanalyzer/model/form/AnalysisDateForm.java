package br.com.emendes.transactionanalyzer.model.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalysisDateForm {

  @NotEmpty
  private String month;

  @NotEmpty
  private String year;

  public Integer getMonthAsInteger() {
    return Integer.valueOf(month);
  }

  public Integer getYearAsInteger() {
    return Integer.valueOf(year);
  }

}
