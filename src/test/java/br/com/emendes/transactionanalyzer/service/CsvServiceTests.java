package br.com.emendes.transactionanalyzer.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.emendes.transactionanalyzer.model.util.RawTransaction;
import br.com.emendes.transactionanalyzer.util.MultipartFileCreator;

@DisplayName("Tests for CsvService")
@ExtendWith(SpringExtension.class)
public class CsvServiceTests {

  @InjectMocks
  private CsvService csvService;

  @Test
  @DisplayName("Must return list of rawTransaction when file is valid")
  void mustReturnListOfRawTransactionWhenFileIsValid() throws IOException {
    InputStream inputStreamCsv = MultipartFileCreator.validFile().getInputStream();

    List<RawTransaction> rawTransactions = csvService.readCsvInputStream(inputStreamCsv);

    Assertions.assertThat(rawTransactions)
        .isNotEmpty()
        .hasSize(6);
  }

  @Test
  @DisplayName("Must return list of rawTransaction with 1 element when file has 1 invalid transaction")
  void mustReturnListOfRawTransactionWhenFileIsInvalid() throws IOException {
    InputStream inputStreamCsv = MultipartFileCreator.invalidTransaction().getInputStream();

    List<RawTransaction> rawTransactions = csvService.readCsvInputStream(inputStreamCsv);

    Assertions.assertThat(rawTransactions)
        .isNotEmpty()
        .hasSize(1);
  }

  @Test
  @DisplayName("Must return list of rawTransaction when file is bigger")
  void mustReturnListOfRawTransactionWhenFileIsBigger() throws IOException {
    InputStream inputStreamCsv = MultipartFileCreator.biggerFile().getInputStream();

    List<RawTransaction> rawTransactions = csvService.readCsvInputStream(inputStreamCsv);

    Assertions.assertThat(rawTransactions)
        .isNotEmpty()
        .hasSize(2400);
  }

  @Test
  @DisplayName("Must return list of rawTransaction when file is separated by semicolon")
  void mustReturnListOfRawTransactionWhenFileIsSeparatedBySemicolon() throws IOException {
    InputStream inputStreamCsv = MultipartFileCreator.fileSeparatedBySemicolon().getInputStream();

    List<RawTransaction> rawTransactions = csvService.readCsvInputStream(inputStreamCsv);

    Assertions.assertThat(rawTransactions)
        .isNotEmpty()
        .hasSize(1);

    Assertions.assertThat(rawTransactions.get(0).getOriginBranch())
        .isNull();
  }

}
