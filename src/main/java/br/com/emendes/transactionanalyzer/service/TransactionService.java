package br.com.emendes.transactionanalyzer.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.emendes.transactionanalyzer.util.ReadFile;

@Service
public class TransactionService {

  public void saveAll(MultipartFile file) {
    ReadFile.readMultipartFile(file);
  }

}
