package br.com.emendes.transactionanalyzer.validation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public abstract class TransactionsValidator {

  /**
   * Método para validar e filtrar as transações
   * <p>
   * validações:
   * </p>
   * <ul>
   * <li>A transação deve ter 8 campos</li>
   * <li>Nenhum campo pode estar vazio</li>
   * <li>O campo value (7) deve ser um BigDecimal válido</li>
   * <li>O campo dateTime (8) deve ser um LocalDateTime válido</li>
   * </ul>
   * 
   * @param rawTransactions
   * @return Lista com as transações válidas.
   */
  public static List<String> validate(List<String> rawTransactions) {
    List<String> validateTransactions = rawTransactions.stream()
        .filter(tl -> isValid(tl))
        .toList();

    return validateTransactions;
  }

  private static boolean isValid(String transactionLine) {

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
      new BigDecimal(value);
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
