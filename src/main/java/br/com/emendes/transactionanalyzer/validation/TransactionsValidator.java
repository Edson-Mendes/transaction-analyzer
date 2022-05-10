package br.com.emendes.transactionanalyzer.validation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public abstract class TransactionsValidator {

  public static boolean isValid(String transactionLine) {

    String[] fields = transactionLine.split(",");
    if (fields.length != 8) {
      return false;
    }
    for (String field : fields) {
      if (field.isBlank()) {
        return false;
      }
    }
    String value = fields[6];
    String dateTime = fields[7];

    return validValue(value) && validDateTime(dateTime);
  }

  private static boolean validValue(String value) {
    try {
      BigDecimal valueBigDecimal = new BigDecimal(value);
      if (valueBigDecimal.setScale(2, RoundingMode.DOWN).compareTo(BigDecimal.ZERO) <= 0) {
        return false;
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private static boolean validDateTime(String dateTime) {
    try {
      LocalDateTime.parse(dateTime);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

}
