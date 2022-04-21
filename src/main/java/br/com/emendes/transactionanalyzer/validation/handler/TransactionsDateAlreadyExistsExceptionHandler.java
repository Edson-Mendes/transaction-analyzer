package br.com.emendes.transactionanalyzer.validation.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import br.com.emendes.transactionanalyzer.validation.exception.TransactionsDateAlreadyExistsException;

@ControllerAdvice
public class TransactionsDateAlreadyExistsExceptionHandler {

  @ExceptionHandler(value = TransactionsDateAlreadyExistsException.class)
  public RedirectView handle(TransactionsDateAlreadyExistsException exception, RedirectAttributes attributes) {
    attributes.addFlashAttribute("error", exception.getMessage());

    return new RedirectView("/transactions");
  }
}
