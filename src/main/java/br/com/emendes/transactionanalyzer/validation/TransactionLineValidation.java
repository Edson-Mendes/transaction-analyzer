package br.com.emendes.transactionanalyzer.validation;

public abstract class TransactionLineValidation {

  /**
   * Valída se a String corresponde aos dados de uma transação financeira.
   * Para ser válido precisa ter 8 campos separados por vírgula (,) e os campos
   * não devem estar em branco.
   * 
   * @param transactionLine
   * @return true se for uma string válida e falso caso contrário
   */
  public static boolean isValid(String transactionLine) {
    // TODO: Melhorar a validação.
    String[] fields = transactionLine.split(",");
    if (fields.length != 8) {
      return false;
    }
    for (String field : fields) {
      if (field.isBlank()) {
        return false;
      }
    }

    return true;
  }

}
