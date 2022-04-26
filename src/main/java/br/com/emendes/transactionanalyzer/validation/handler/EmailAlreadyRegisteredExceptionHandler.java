package br.com.emendes.transactionanalyzer.validation.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import br.com.emendes.transactionanalyzer.model.AlertType;
import br.com.emendes.transactionanalyzer.model.Message;
import br.com.emendes.transactionanalyzer.validation.exception.EmailAlreadyRegisteredException;

@ControllerAdvice
public class EmailAlreadyRegisteredExceptionHandler {

  @ExceptionHandler(value = EmailAlreadyRegisteredException.class)
  public RedirectView handle(EmailAlreadyRegisteredException exception, RedirectAttributes attributes) {
    final Message message = Message.builder()
        .type(AlertType.ERROR)
        .message(exception.getMessage())
        .build();
    attributes.addFlashAttribute("message", message);

    return new RedirectView("/users/register");
  }

}
