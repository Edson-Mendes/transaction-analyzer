package br.com.emendes.transactionanalyzer.form;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.emendes.transactionanalyzer.controller.form.FileForm;
import br.com.emendes.transactionanalyzer.util.MultipartFileCreator;

@DisplayName("Tests for FileForm")
public class FileFormTests {

  @Test
  @DisplayName("Must return the correct file type")
  void mustReturnTheFileType() {
    FileForm fileForm = new FileForm(MultipartFileCreator.validFile());

    String extension = fileForm.getFileExtension();

    Assertions.assertThat(extension).isEqualTo("csv");
  }

}
