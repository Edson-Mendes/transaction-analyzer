package br.com.emendes.transactionanalyzer.util.creator;

import br.com.emendes.transactionanalyzer.model.util.RawTransaction;

public class RawTransactionCreator {

  /**
   * Valid RawTransaction
   */
  public static RawTransaction validRawTransaction() {
    return RawTransaction.builder()
        .originBank("BANCO DO BRASIL")
        .originBranch("0009")
        .originAccount("005555-6")
        .destinationBank("PICPAY")
        .destinationBranch("1234")
        .destinationAccount("009876-5")
        .value("5000.00")
        .dateTime("2022-05-21T10:39:07")
        .build();
  }

  /**
   * RawTransaction with some null attributes.
   */
  public static RawTransaction rawTransactionWithNullField() {
    return RawTransaction.builder()
        .originBank("BANCO DO BRASIL")
        .originBranch("0009")
        .originAccount("005555-6")
        .destinationBank(null)
        .destinationBranch("1234")
        .destinationAccount("009876-5")
        .value("5000.00")
        .dateTime("2022-05-21T10:39:07")
        .build();
  }

  /**
   * RawTransaction with some blank attributes.
   */
  public static RawTransaction rawTransactionWithBlankField() {
    return RawTransaction.builder()
        .originBank("BANCO DO BRASIL")
        .originBranch("0009")
        .originAccount("005555-6")
        .destinationBank("PICPAY")
        .destinationBranch("")
        .destinationAccount("009876-5")
        .value("5000.00")
        .dateTime("2022-05-21T10:39:07")
        .build();
  }

  /**
   * RawTransaction com {@code dateTime} do valor que você quiser.
   * 
   * @param value
   * @return {@code RawTransaction} com todos os campos válidos e {@code dateTime}
   *         de acordo com o parâmetro.
   */
  public static RawTransaction withDateTime(String dateTime) {
    return RawTransaction.builder()
        .originBank("BANCO DO BRASIL")
        .originBranch("0009")
        .originAccount("005555-6")
        .destinationBank("PICPAY")
        .destinationBranch("0098")
        .destinationAccount("009876-5")
        .value("5000.00")
        .dateTime(dateTime)
        .build();
  }

  /**
   * RawTransaction com {@code value} do valor que você quiser.
   * 
   * @param value
   * @return {@code RawTransaction} com todos os campos válidos e {@code value} de
   *         acordo com o parâmetro.
   */
  public static RawTransaction withValue(String value) {
    return RawTransaction.builder()
        .originBank("BANCO DO BRASIL")
        .originBranch("0009")
        .originAccount("005555-6")
        .destinationBank("PICPAY")
        .destinationBranch("0098")
        .destinationAccount("009876-5")
        .value(value)
        .dateTime("2022-05-21T10:39:07")
        .build();
  }

}
