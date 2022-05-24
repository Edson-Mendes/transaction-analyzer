package br.com.emendes.transactionanalyzer.controller.form;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import br.com.emendes.transactionanalyzer.validation.annotation.CsvValidation;
import br.com.emendes.transactionanalyzer.validation.annotation.FileNotEmpty;
import br.com.emendes.transactionanalyzer.validation.annotation.MaxSizeFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileForm {

  @NotNull
  @CsvValidation
  @FileNotEmpty
  @MaxSizeFile(size = 131072l)
  private MultipartFile file;

}
