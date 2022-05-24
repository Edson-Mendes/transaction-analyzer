package br.com.emendes.transactionanalyzer.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.emendes.transactionanalyzer.model.util.AlertType;
import br.com.emendes.transactionanalyzer.model.util.Message;

@ControllerAdvice
public class BindExceptionHandler {

  @ExceptionHandler(BindException.class)
  public String handle(BindException exception, RedirectAttributes attributes, HttpServletRequest request) {
    String errorMessage = exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();

    final Message message = Message.builder()
        .type(AlertType.ERROR)
        .message(errorMessage)
        .build();
    attributes.addFlashAttribute("message", message);
    return "redirect:" + request.getRequestURI();
  }
}
