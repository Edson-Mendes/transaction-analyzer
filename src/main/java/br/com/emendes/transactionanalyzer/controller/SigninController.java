package br.com.emendes.transactionanalyzer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.emendes.transactionanalyzer.model.form.SigninForm;

@Controller
@RequestMapping("/signin")
public class SigninController {

  @GetMapping
  public String signupForm(SigninForm signinForm) {
    return "screen/signin";
  }

}
