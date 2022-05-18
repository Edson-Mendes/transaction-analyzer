package br.com.emendes.transactionanalyzer.model.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.com.emendes.transactionanalyzer.validation.TransactionValidator;
import lombok.Data;

/**
 * Classe que recebe os dados brutos de uma transação.
 */
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

    this.value = new BigDecimal(fields[6]).setScale(2, RoundingMode.DOWN);
    this.dateTime = LocalDateTime.parse(fields[7]);
  }

  /**
   * Filtra e converte uma lista de string (transações) em uma lista de
   * RawTransaction
   */
  public static List<RawTransaction> fromTransactionsLines(List<String> transactionLines) {
    List<RawTransaction> transactions = transactionLines
        .stream()
        .filter(tl -> TransactionValidator.validate(tl))
        .map(tlv -> new RawTransaction(tlv))
        .toList();

    return transactions;
  }

  /**
   * Filtra transações por data
   * 
   * @param transactions     lista de transações a serem filtradas
   * @param transactionsDate data válida
   * @return Lista filtrada de transações
   */
  public static List<RawTransaction> filterByDate(List<RawTransaction> transactions, LocalDate transactionsDate) {
    return transactions
        .stream()
        .filter(rt -> rt.getDateTime().toLocalDate().equals(transactionsDate))
        .toList();
  }

}
