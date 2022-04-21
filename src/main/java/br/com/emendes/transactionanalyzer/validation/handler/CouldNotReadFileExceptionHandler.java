package br.com.emendes.transactionanalyzer.validation.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import br.com.emendes.transactionanalyzer.validation.exception.CouldNotReadFileException;

@ControllerAdvice
public class CouldNotReadFileExceptionHandler {

  @ExceptionHandler(value = CouldNotReadFileException.class)
  public RedirectView handle(CouldNotReadFileException exception, RedirectAttributes attributes) {
    attributes.addFlashAttribute("error", exception.getMessage());

    return new RedirectView("/transactions");
  }
}
