package br.com.emendes.transactionanalyzer.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.emendes.transactionanalyzer.model.dto.UserDto;
import br.com.emendes.transactionanalyzer.model.form.UserForm;
import br.com.emendes.transactionanalyzer.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  public ModelAndView userPage() {
    List<UserDto> users = userService.readAll();

    ModelAndView modelAndView = new ModelAndView("page/usersPage");
    modelAndView.addObject("users", users);

    return modelAndView;
  }

  @GetMapping("/register")
  public String registerUserPage(UserForm userForm) {
    return "page/registerUserPage";
  }

  @PostMapping("/register")
  public String registerUser(@Valid UserForm userForm, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "page/registerUserPage";
    }

    userService.create(userForm);
    return "redirect:/users";
  }

  @DeleteMapping("/{id}")
  public String deleteUser(@PathVariable Long id, Principal principal) {

    userService.deleteById(id, principal.getName());
    return "redirect:/users";
  }

}
