package br.com.emendes.transactionanalyzer.service;

import org.springframework.stereotype.Service;

import br.com.emendes.transactionanalyzer.model.Authority;
import br.com.emendes.transactionanalyzer.model.User;
import br.com.emendes.transactionanalyzer.model.form.UserForm;
import br.com.emendes.transactionanalyzer.repository.UserRepository;
import br.com.emendes.transactionanalyzer.util.PasswordGenerator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public void create(UserForm userForm) {
    if (userRepository.existsByEmail(userForm.getEmail())) {
      // TODO: Lançar uma exception avisando que o email está em uso.
    }

    User user = userForm.toUser();
    user.setPassword(PasswordGenerator.generate());
    Authority userAuthority = new Authority("USER");
    user.addAuthority(userAuthority);
    userRepository.save(user);
  }

}
