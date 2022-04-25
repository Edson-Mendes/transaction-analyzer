package br.com.emendes.transactionanalyzer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.emendes.transactionanalyzer.model.form.UserForm;
import br.com.emendes.transactionanalyzer.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignupController {

  private final UserService userService;

  @GetMapping
  public String signupForm(UserForm userForm) {
    return "page/signup";
  }

  @PostMapping
  public String submitForm(@Valid UserForm userForm, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "page/signup";
    }

    userService.create(userForm);
    return "redirect:/transactions";
  }

}
