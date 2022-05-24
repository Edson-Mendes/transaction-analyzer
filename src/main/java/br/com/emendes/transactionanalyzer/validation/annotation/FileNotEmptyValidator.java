package br.com.emendes.transactionanalyzer.validation.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class FileNotEmptyValidator implements ConstraintValidator<FileNotEmpty, MultipartFile> {

  @Override
  public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
    if (file == null) {
      return true;
    }
    return !file.isEmpty();
  }

}
