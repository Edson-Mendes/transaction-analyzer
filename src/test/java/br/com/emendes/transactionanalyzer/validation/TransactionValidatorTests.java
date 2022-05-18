package br.com.emendes.transactionanalyzer.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Tests for Transaction Validator")
public class TransactionValidatorTests {

  @Test
  @DisplayName("Return true when transactionLine is valid")
  void returnTrueWhenTransactionIsValid() {
    String validTransactionLine1 = "BANCO DO BRASIL,1234,01111-1,BANCO BRADESCO,5678,02222-2,500,2022-02-04T07:30:00";
    String validTransactionLine2 = "BANCO BANRISUL,2365,13322-9,BANCO BRADESCO,7777,01144-2,25000,2022-05-29T18:27:00";

    Assertions.assertTrue(TransactionValidator.validate(validTransactionLine1));
    Assertions.assertTrue(TransactionValidator.validate(validTransactionLine2));
  }

  @Test
  @DisplayName("Return false when transactionLine contains blank fields")
  void returnFalseWhenTransactionContainsBlankFields() {
    String orgindBranchIsBlank = "BANCO DO BRASIL,,01111-1,BANCO BRADESCO,5678,02222-2,500,2022-02-04T07:30:00";
    String destinationbankIsBlank = "BANCO DO BRASIL,1234,01111-1,,5678,02222-2,500,2022-02-04T07:30:00";
    String allFieldsAreBlank = ",,,,,,,";

    Assertions.assertFalse(TransactionValidator.validate(orgindBranchIsBlank));
    Assertions.assertFalse(TransactionValidator.validate(destinationbankIsBlank));
    Assertions.assertFalse(TransactionValidator.validate(allFieldsAreBlank));
  }

  @Test
  @DisplayName("Return false when transactionLine not contains 8 fields")
  void returnFalseWhenTransactionNotContains8Fields() {
    String only7Fields = "BANCO BANRISUL,2365,13322-9,7777,01144-2,25000,2022-05-29T18:27:00";
    String only5Fields = "BANCO BANRISUL,13322-9,01144-2,25000,2022-05-29T18:27:00";
    String zeroFields = "";

    Assertions.assertFalse(TransactionValidator.validate(only7Fields));
    Assertions.assertFalse(TransactionValidator.validate(only5Fields));
    Assertions.assertFalse(TransactionValidator.validate(zeroFields));
  }

  @Test
  @DisplayName("Return false when value is invalid")
  void returnFalseWhenValueIsInvalid() {
    String negativeValue = "BANCO DO BRASIL,1234,01111-1,BANCO BRADESCO,5678,02222-2,-500,2022-02-04T07:30:00";
    String valueIsZero = "BANCO BANRISUL,2365,13322-9,BANCO BRADESCO,7777,01144-2,0,2022-05-29T18:27:00";
    String valueIsLessThenMin = "BANCO DO BRASIL,1234,01111-1,BANCO BRADESCO,5678,02222-2,0.009,2022-02-04T07:30:00";
    String valueIsBiggerThenMax = "BANCO DO BRASIL,1234,01111-1,BANCO BRADESCO,5678,02222-2,10000000000000.00,2022-02-04T07:30:00";

    Assertions.assertFalse(TransactionValidator.validate(negativeValue));
    Assertions.assertFalse(TransactionValidator.validate(valueIsZero));
    Assertions.assertFalse(TransactionValidator.validate(valueIsLessThenMin));
    Assertions.assertFalse(TransactionValidator.validate(valueIsBiggerThenMax));
  }

  @Test
  @DisplayName("Return false when dateTime is invalid")
  void returnFalseWhenDateTimeIsInvalid() {
    String unparsedDateTime = "BANCO DO BRASIL,1234,01111-1,BANCO BRADESCO,5678,02222-2,500,2O22-O2-04T07:30:OO";
    String dateTimeLessThenMin = "BANCO BANRISUL,2365,13322-9,BANCO BRADESCO,7777,01144-2,25000,1969-12-31T23:59:59";
    String dateTimeBiggerThenMax = "BANCO BANRISUL,2365,13322-9,BANCO BRADESCO,7777,01144-2,25000,2100-01-01T00:00:00";

    Assertions.assertFalse(TransactionValidator.validate(unparsedDateTime));
    Assertions.assertFalse(TransactionValidator.validate(dateTimeLessThenMin));
    Assertions.assertFalse(TransactionValidator.validate(dateTimeBiggerThenMax));
  }

}
