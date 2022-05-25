package br.com.emendes.transactionanalyzer.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import br.com.emendes.transactionanalyzer.validation.annotation.FileNotEmpty;

public class FileNotEmptyValidator implements ConstraintValidator<FileNotEmpty, MultipartFile> {

  @Override
  public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
    if (file == null) {
      return true;
    }
    return !file.isEmpty();
  }

}
