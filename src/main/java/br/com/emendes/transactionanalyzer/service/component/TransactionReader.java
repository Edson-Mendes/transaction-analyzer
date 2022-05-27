package br.com.emendes.transactionanalyzer.service.component;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.emendes.transactionanalyzer.model.util.RawTransaction;

public interface TransactionReader {

  List<RawTransaction> read(MultipartFile file);

}
