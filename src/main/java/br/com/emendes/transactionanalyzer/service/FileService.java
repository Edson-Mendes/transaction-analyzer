package br.com.emendes.transactionanalyzer.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.emendes.transactionanalyzer.model.util.RawTransaction;
import br.com.emendes.transactionanalyzer.service.component.TransactionReader;

@Service
public class FileService {

  public List<RawTransaction> readFile(MultipartFile file, TransactionReader transactionReader) {
    return transactionReader.read(file);
  }

}
