package br.com.emendes.transactionanalyzer.validation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class ValueValidator {

  /**
   * <p>
   * Método para validar o valor de transferência de uma transação financeira
   * </p>
   * <p>
   * Value é válido se estiver entre 0,01 e 9.999.999.999.999,00.
   * </p>
   * <p>
   * Dígitos abaixo de 2 casas decimais são descartados ao validar ou seja,
   * 0.01234 será considerado 0.01.
   * </p>
   * 
   * @param value
   * @return True se o value é válido, False caso contrário.
   */
  public static Boolean validate(String value) {
    BigDecimal minValue = new BigDecimal("0.01");
    BigDecimal maxValue = new BigDecimal("9999999999999.99");

    try {
      BigDecimal valueBigDecimal = new BigDecimal(value).setScale(2, RoundingMode.DOWN);
      if (valueBigDecimal.compareTo(minValue) < 0 || valueBigDecimal.compareTo(maxValue) > 0) {
        return false;
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }

}
