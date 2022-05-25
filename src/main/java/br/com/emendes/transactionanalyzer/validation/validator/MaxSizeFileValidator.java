package br.com.emendes.transactionanalyzer.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import br.com.emendes.transactionanalyzer.validation.annotation.MaxSizeFile;

public class MaxSizeFileValidator implements ConstraintValidator<MaxSizeFile, MultipartFile> {

  private long size;

  @Override
  public void initialize(MaxSizeFile constraintAnnotation) {
    this.size = constraintAnnotation.size();
  }

  @Override
  public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
    if (file == null) {
      return true;
    }
    return file.getSize() <= size;
  }

}
