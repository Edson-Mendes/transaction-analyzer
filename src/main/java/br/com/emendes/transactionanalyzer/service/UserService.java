package br.com.emendes.transactionanalyzer.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.emendes.transactionanalyzer.model.Authority;
import br.com.emendes.transactionanalyzer.model.User;
import br.com.emendes.transactionanalyzer.model.dto.UserDto;
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

  public List<UserDto> readAll() {
    // TODO: Paginar a busca por usuários.

    Authority authority = new Authority("ADMIN");
    List<User> users = userRepository.findByAuthority(authority);

    List<UserDto> usersDto = UserDto.fromUserList(users);

    return usersDto;
  }

  @Transactional
  public void deleteById(Long id, String email) {
    userRepository.deleteByIdWhereEmailNotEquals(id, email);
  }

}
