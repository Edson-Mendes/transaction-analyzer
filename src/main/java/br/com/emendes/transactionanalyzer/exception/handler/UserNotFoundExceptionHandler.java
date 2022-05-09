package br.com.emendes.transactionanalyzer.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import br.com.emendes.transactionanalyzer.exception.UserNotFoundException;
import br.com.emendes.transactionanalyzer.model.util.AlertType;
import br.com.emendes.transactionanalyzer.model.util.Message;

@ControllerAdvice
public class UserNotFoundExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public RedirectView handle(UserNotFoundException exception, RedirectAttributes attributes) {
    final Message message = Message.builder()
        .type(AlertType.ERROR)
        .message(exception.getMessage())
        .build();
    attributes.addFlashAttribute("message", message);

    return new RedirectView("/users");
  }

}