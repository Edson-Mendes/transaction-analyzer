package br.com.emendes.transactionanalyzer.validation;

import java.time.LocalDateTime;

public abstract class DateTimeValidator {

  /**
   * <p>
   * Método para validar a data e hora de uma transação financeira
   * </p>
   * <p>
   * DateTime é válido se estiver no formato aaaa-MM-ddThh:mm:ss
   * e se for um DateTime entre 1970-01-01T00:00:00 (incluso) até
   * 2099-12-31T23:59:59 (incluso).
   * </p>
   * <p>
   * Exemplo: 2022-05-17T17:09:35
   * </p>
   * 
   * @param dateTime
   * @return True se o dateTime for válido, falso caso contrário.
   */
  public static Boolean validate(String dateTime) {
    try {
      LocalDateTime localDateTime = LocalDateTime.parse(dateTime);

      // LocalDateTime minDateTime = LocalDateTime.parse("1970-01-01T00:00:00");
      LocalDateTime minDateTime = LocalDateTime.of(1970, 01, 01, 00, 00, 00);
      LocalDateTime maxDateTime = LocalDateTime.of(2099, 12, 31, 23, 59, 59);

      if (localDateTime.compareTo(minDateTime) < 0 || localDateTime.compareTo(maxDateTime) > 0) {
        return false;
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }

}
