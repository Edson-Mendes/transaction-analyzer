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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import br.com.emendes.transactionanalyzer.model.AlertType;
import br.com.emendes.transactionanalyzer.model.Message;
import br.com.emendes.transactionanalyzer.model.User;
import br.com.emendes.transactionanalyzer.model.dto.UserDto;
import br.com.emendes.transactionanalyzer.model.form.UpdateUserForm;
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

  @GetMapping("/update/{id}")
  public ModelAndView updateUserPage(@PathVariable Long id) {
    ModelAndView modelAndView = new ModelAndView("page/updateUserPage");

    User user = userService.findById(id);
    UpdateUserForm updateUser = new UpdateUserForm(user.getName(), user.getEmail());
    modelAndView.addObject("updateForm", updateUser);
    modelAndView.addObject("id", id);

    return modelAndView;
  }

  @PostMapping("/register")
  public String registerUser(@Valid UserForm userForm, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "page/registerUserPage";
    }

    userService.create(userForm);
    return "redirect:/users";
  }

  @PutMapping("/update/{id}")
  public RedirectView updateUser(@PathVariable Long id, @Valid UpdateUserForm updateForm, BindingResult bindingResult,
      RedirectAttributes attributes) {
    if (bindingResult.hasErrors()) {
      final Message message = Message.builder()
          .type(AlertType.ERROR)
          .message("Invalid name")
          .build();
      attributes.addFlashAttribute("message", message);
      return new RedirectView("/users/update/" + id);
    }

    userService.update(id, updateForm);

    return new RedirectView("/users");
  }

  @DeleteMapping("/{id}")
  public String deleteUser(@PathVariable Long id, Principal principal) {

    // userService.deleteById(id, principal.getName());
    userService.disableUser(id, principal.getName());
    return "redirect:/users";
  }

}
