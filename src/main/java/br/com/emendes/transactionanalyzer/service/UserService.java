package br.com.emendes.transactionanalyzer.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.emendes.transactionanalyzer.exception.EmailAlreadyRegisteredException;
import br.com.emendes.transactionanalyzer.exception.UserNotFoundException;
import br.com.emendes.transactionanalyzer.model.dto.UserDto;
import br.com.emendes.transactionanalyzer.model.entity.Authority;
import br.com.emendes.transactionanalyzer.model.entity.User;
import br.com.emendes.transactionanalyzer.model.form.UpdateUserForm;
import br.com.emendes.transactionanalyzer.model.form.UserForm;
import br.com.emendes.transactionanalyzer.repository.UserRepository;
import br.com.emendes.transactionanalyzer.util.Encoder;
import br.com.emendes.transactionanalyzer.util.PasswordGenerator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final EmailService emailService;

  public void create(UserForm userForm) {
    if (userRepository.existsByEmail(userForm.getEmail())) {
      throw new EmailAlreadyRegisteredException("This email address is already registered!");
    }

    User user = userForm.toUser();
    String password = PasswordGenerator.generate();

    emailService.sendEmail(user, password);

    user.setPassword(Encoder.encrypt(password));
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
    Authority authority = new Authority("ADMIN");
    userRepository.deleteByIdWhereEmailNotEqualsAndNotAdmin(id, email, authority);
  }

  public void disableUser(Long id, String email) {
    Authority authority = new Authority("ADMIN");
    User user = userRepository.findByIdWhereEmailNotEqualsAndNotAdmin(id, email, authority)
        .orElseThrow(() -> {
          throw new UserNotFoundException("User not found or can not be deleted");
        });

    user.setEnabled(false);
    userRepository.save(user);
  }

  public void update(Long id, @Valid UpdateUserForm updateForm) {
    User user = userRepository.findById(id).orElseThrow(() -> {
      throw new UserNotFoundException("User not found");
    });

    user.setName(updateForm.getName());
    userRepository.save(user);
  }

  public User findById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> {
      throw new UserNotFoundException("User not found");
    });
  }

  public User findByEmail(String email) {
    return userRepository.findByEmail(email).orElseThrow(() -> {
      throw new UserNotFoundException("User not found");
    });
  }

}
