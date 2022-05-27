package br.com.emendes.transactionanalyzer.service.component;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.emendes.transactionanalyzer.exception.CouldNotReadFileException;
import br.com.emendes.transactionanalyzer.model.util.RawTransaction;

@Component(value = "CsvTransactionReader")
public class CsvTransactionReader implements TransactionReader {

  private final CsvMapper mapper;
  private final CsvSchema schema;

  public CsvTransactionReader() {
    mapper = new CsvMapper();
    mapper.registerModule(new JavaTimeModule());
    schema = mapper.schemaFor(RawTransaction.class);
  }

  @Override
  public List<RawTransaction> read(MultipartFile file) {
    try {
      MappingIterator<RawTransaction> values = mapper.readerFor(RawTransaction.class)
          .with(schema).readValues(file.getInputStream());

      return values.readAll();
    } catch (IOException e) {
      String message = String.format("Could not read the file %s", file.getOriginalFilename());
      throw new CouldNotReadFileException(message);
    }
  }

}
