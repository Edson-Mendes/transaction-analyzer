package br.com.emendes.transactionanalyzer.validation.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

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
