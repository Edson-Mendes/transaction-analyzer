package br.com.emendes.transactionanalyzer.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import br.com.emendes.transactionanalyzer.exception.InvalidFileException;
import br.com.emendes.transactionanalyzer.model.util.AlertType;
import br.com.emendes.transactionanalyzer.model.util.Message;

@ControllerAdvice
public class InvalidFileExceptionHandler {

  @ExceptionHandler(value = InvalidFileException.class)
  public RedirectView handle(InvalidFileException exception, RedirectAttributes attributes) {
    final Message message = Message.builder()
        .type(AlertType.ERROR)
        .message(exception.getMessage())
        .build();
    attributes.addFlashAttribute("message", message);

    return new RedirectView("/transactions");
  }

}
