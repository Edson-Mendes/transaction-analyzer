package br.com.emendes.transactionanalyzer.model.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class RawTransaction {

  private String originBank;
  private String originBranch;
  private String originAccount;

  private String destinationBank;
  private String destinationBranch;
  private String destinationAccount;

  private BigDecimal value;
  private LocalDateTime dateTime;

  public RawTransaction(String transactionLine) {
    String[] fields = transactionLine.split(",");

    this.originBank = fields[0];
    this.originBranch = fields[1];
    this.originAccount = fields[2];

    this.destinationBank = fields[3];
    this.destinationBranch = fields[4];
    this.destinationAccount = fields[5];

    this.value = new BigDecimal(fields[6]);
    this.dateTime = LocalDateTime.parse(fields[7]);
  }

  public static List<RawTransaction> from(List<String> transactionLines) {
    List<RawTransaction> transactions = transactionLines
        .stream()
        .filter(tl -> isValid(tl))
        .map(tlv -> new RawTransaction(tlv))
        .toList();

    return transactions;
  }

  /**
   * Método para validar transações
   * <p>
   * <b>validações:</b>
   * </p>
   * <ul>
   * <li>A transação deve ter 8 campos</li>
   * <li>Nenhum campo pode estar vazio</li>
   * <li>O campo value (7) deve ser um BigDecimal válido</li>
   * <li>O campo dateTime (8) deve ser um LocalDateTime válido</li>
   * </ul>
   */
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

  public static List<RawTransaction> filterByDate(List<RawTransaction> transactions, LocalDate transactionsDate) {
    return transactions
        .stream()
        .filter(rt -> rt.getDateTime().toLocalDate().equals(transactionsDate))
        .toList();
  }

}
