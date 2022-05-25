package br.com.emendes.transactionanalyzer.validation;

// FIXME: DELETAR, provavelmente não vou usar mais.
public abstract class TransactionValidator {

  /**
   * Método para validar linha de transação
   * <p>
   * Uma linha de transação é válida se possuir 8 campos não vazios, e value e
   * dateTime sejam válidos, exemplo:
   * </p>
   * <p>
   * PICPAY,1234,001234-5,NUBANK,4321,005432-1,100.00,2022-05-01T18:34:51
   * </p>
   * <p>
   * NOTA: Futuramente pretendo adicionar validação para Conta e Agência
   * </p>
   * 
   * @param transactionLine
   * @return True se a transação estiver no padrão válido, false caso contrário
   */
  public static boolean validate(String transactionLine) {

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

    return ValueValidator.validate(value) && DateTimeValidator.validate(dateTime);
  }

}
