package br.com.emendes.transactionanalyzer.service;

import java.io.IOException;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

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
    MultipartFile validFile = MultipartFileCreator.validFile();

    List<RawTransaction> rawTransactions = csvService.readFile(validFile);

    Assertions.assertThat(rawTransactions)
        .isNotEmpty()
        .hasSize(6);
  }

  @Test
  @DisplayName("Must return list of rawTransaction with 1 element when file has 1 invalid transaction")
  void mustReturnListOfRawTransactionWhenFileIsInvalid() throws IOException {
    MultipartFile invalidTransactionFile = MultipartFileCreator.invalidTransaction();

    List<RawTransaction> rawTransactions = csvService.readFile(invalidTransactionFile);

    Assertions.assertThat(rawTransactions)
        .isNotEmpty()
        .hasSize(1);
  }

  @Test
  @DisplayName("Must return list of rawTransaction when file is bigger")
  void mustReturnListOfRawTransactionWhenFileIsBigger() throws IOException {
    MultipartFile bigFile = MultipartFileCreator.biggerFile();

    List<RawTransaction> rawTransactions = csvService.readFile(bigFile);

    Assertions.assertThat(rawTransactions)
        .isNotEmpty()
        .hasSize(2400);
  }

  @Test
  @DisplayName("Must return list of rawTransaction when file is separated by semicolon")
  void mustReturnListOfRawTransactionWhenFileIsSeparatedBySemicolon() throws IOException {
    MultipartFile fileSeparatedBySemicolon = MultipartFileCreator.fileSeparatedBySemicolon();

    List<RawTransaction> rawTransactions = csvService.readFile(fileSeparatedBySemicolon);

    Assertions.assertThat(rawTransactions)
        .isNotEmpty()
        .hasSize(1);

    Assertions.assertThat(rawTransactions.get(0).getOriginBranch())
        .isNull();
  }

}
