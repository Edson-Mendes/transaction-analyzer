package br.com.emendes.transactionanalyzer.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.stereotype.Service;

import br.com.emendes.transactionanalyzer.model.util.RawTransaction;

@Service
public class CsvService {

  private final CsvMapper mapper = new CsvMapper();
  private final CsvSchema schema = mapper.schemaFor(RawTransaction.class);

  public CsvService() {
    mapper.registerModule(new JavaTimeModule());
  }

  public List<RawTransaction> readCsvInputStream(InputStream inputStreamCsv) throws IOException {
    MappingIterator<RawTransaction> values = mapper.readerFor(RawTransaction.class)
        .with(schema).readValues(inputStreamCsv);

    return values.readAll();
  }
}
