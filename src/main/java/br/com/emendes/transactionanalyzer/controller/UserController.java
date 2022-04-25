package br.com.emendes.transactionanalyzer.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.emendes.transactionanalyzer.model.dto.UserDto;
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

    ModelAndView modelAndView = new ModelAndView("page/users");
    modelAndView.addObject("users", users);

    return modelAndView;
  }

}
