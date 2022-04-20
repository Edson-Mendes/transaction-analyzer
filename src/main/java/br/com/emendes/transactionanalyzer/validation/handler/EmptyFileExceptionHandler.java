package br.com.emendes.transactionanalyzer.validation.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import br.com.emendes.transactionanalyzer.validation.exception.EmptyFileException;

@ControllerAdvice
public class EmptyFileExceptionHandler {

  @ExceptionHandler(value = EmptyFileException.class)
  public RedirectView handle(EmptyFileException exception, RedirectAttributes attributes) {
    attributes.addFlashAttribute("error", exception.getMessage());

    return new RedirectView("/home");
  }

}
