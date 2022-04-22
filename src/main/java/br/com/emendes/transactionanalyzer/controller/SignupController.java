package br.com.emendes.transactionanalyzer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

  @GetMapping
  public String signupForm() {
    return "screen/signup.html";
  }

}
