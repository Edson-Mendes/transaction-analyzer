package br.com.emendes.transactionanalyzer.service;

import static org.mockito.ArgumentMatchers.eq;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.emendes.transactionanalyzer.controller.dto.UserDto;
import br.com.emendes.transactionanalyzer.controller.form.UpdateUserForm;
import br.com.emendes.transactionanalyzer.controller.form.UserForm;
import br.com.emendes.transactionanalyzer.exception.EmailAlreadyRegisteredException;
import br.com.emendes.transactionanalyzer.exception.UserNotFoundException;
import br.com.emendes.transactionanalyzer.model.entity.Authority;
import br.com.emendes.transactionanalyzer.model.entity.User;
import br.com.emendes.transactionanalyzer.repository.UserRepository;
import br.com.emendes.transactionanalyzer.util.UpdateUserFormCreator;
import br.com.emendes.transactionanalyzer.util.UserCreator;
import br.com.emendes.transactionanalyzer.util.UserFormCreator;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for UserService")
public class UserServiceTests {

  @InjectMocks
  private UserService userService;

  @Mock
  private UserRepository userRepositoryMock;
  @Mock
  private EmailService emailServiceMock;

  @BeforeEach
  void setup() {
    User userCreated = UserCreator.validUser();

    BDDMockito.when(userRepositoryMock.existsByEmail("lorem@email.com"))
        .thenReturn(false);

    BDDMockito.when(userRepositoryMock.save(ArgumentMatchers.any(User.class)))
        .thenReturn(userCreated);

    BDDMockito.when(userRepositoryMock.findById(1000l))
        .thenReturn(Optional.of(userCreated));

    BDDMockito.when(userRepositoryMock.findByEmail("lorem@email.com"))
        .thenReturn(Optional.of(userCreated));

    BDDMockito.when(userRepositoryMock.findByEmail("noexists@email.com"))
        .thenReturn(Optional.empty());

    BDDMockito.when(userRepositoryMock.findById(1001l))
        .thenReturn(Optional.empty());

    BDDMockito.doNothing().when(emailServiceMock)
        .sendEmail(ArgumentMatchers.any(User.class), ArgumentMatchers.anyString());

    BDDMockito.when(userRepositoryMock.findAllNotAdmin(ArgumentMatchers.any(Authority.class)))
        .thenReturn(List.of(userCreated));

    BDDMockito
        .when(userRepositoryMock.findByIdWhereEmailNotEqualsAndNotAdmin(eq(1000l), eq("lorem@email.com"),
            ArgumentMatchers.any(Authority.class)))
        .thenReturn(Optional.of(userCreated));
  }

  @Test
  @DisplayName("Must return user when create successful")
  void mustReturnUserWhenCreateSuccessful() {
    UserForm userToBeCreated = UserFormCreator.validUserForm();

    User userCreated = userService.create(userToBeCreated);

    Assertions.assertThat(userCreated).isNotNull();
    Assertions.assertThat(userCreated.getId()).isNotNull();
    Assertions.assertThat(userCreated.getName()).isEqualTo(userToBeCreated.getName());
    Assertions.assertThat(userCreated.getEmail()).isEqualTo(userToBeCreated.getEmail());
  }

  @Test
  @DisplayName("Must return user when create successful")
  void mustThrowExceptionWhenCreateUserWithExistingEmail() {
    BDDMockito.when(userRepositoryMock.existsByEmail("lorem@email.com"))
        .thenReturn(true);
    UserForm userToBeCreated = UserFormCreator.validUserForm();

    Assertions.assertThatExceptionOfType(EmailAlreadyRegisteredException.class)
        .isThrownBy(() -> userService.create(userToBeCreated))
        .withMessage("This email address is already registered!");
  }

  @Test
  @DisplayName("Must return a list of userDto when read successful")
  void mustReturnListOfUserWhenFindSuccessful() {
    List<UserDto> users = userService.readAll();

    Assertions.assertThat(users)
        .isNotEmpty()
        .hasSize(1);

    Assertions.assertThat(users.get(0).getEmail())
        .isEqualTo("lorem@email.com");
    Assertions.assertThat(users.get(0).getName())
        .isEqualTo("Lorem Ipsum");
    Assertions.assertThat(users.get(0).getId())
        .isEqualTo(1000l);
  }

  @Test
  @DisplayName("Must return a disabled user when successful")
  void mustReturnDisabledUserWhenSuccessful() {
    BDDMockito.when(userRepositoryMock.save(ArgumentMatchers.any(User.class)))
        .thenReturn(UserCreator.disabledUser());

    User disableUser = userService.disableUser(1000l, "lorem@email.com");

    Assertions.assertThat(disableUser).isNotNull();
    Assertions.assertThat(disableUser.getEnabled()).isFalse();
  }

  @Test
  @DisplayName("Must throw an exception when not found user")
  void mustThrowExceptionWhenNotFoundUser() {
    BDDMockito
        .when(userRepositoryMock.findByIdWhereEmailNotEqualsAndNotAdmin(eq(999l), eq("dolor@email.com"),
            ArgumentMatchers.any(Authority.class)))
        .thenReturn(Optional.empty());

    Assertions.assertThatExceptionOfType(UserNotFoundException.class)
        .isThrownBy(() -> userService.disableUser(999l, "dolor@email.com"))
        .withMessage("User not found or can not be deleted");
  }

  @Test
  @DisplayName("Must return updated user when successful")
  void mustReturnUpdatedUserWhenSuccessful() {
    BDDMockito.when(userRepositoryMock.save(ArgumentMatchers.any(User.class)))
        .thenReturn(UserCreator.updatedUser());

    UpdateUserForm updateUserForm = UpdateUserFormCreator.validUpdateUserForm();
    User updateUser = userService.update(1000l, updateUserForm);

    Assertions.assertThat(updateUser).isNotNull();
    Assertions.assertThat(updateUser.getName()).isEqualTo(updateUser.getName());
  }

  @Test
  @DisplayName("Must throw an exception when update user not existing")
  void mustThrowAnExceptionWhenUpdateUserNotExisting() {
    UpdateUserForm updateUserForm = UpdateUserFormCreator.validUpdateUserForm();

    Assertions.assertThatExceptionOfType(UserNotFoundException.class)
        .isThrownBy(() -> userService.update(1001l, updateUserForm))
        .withMessage("User not found");
  }

  @Test
  @DisplayName("Must return user when find by email successfully")
  void mustReturnUserWhenFindByEmailSuccessfully() {
    String emailToBeSearched = "lorem@email.com";

    User userFinded = userService.findByEmail(emailToBeSearched);

    Assertions.assertThat(userFinded).isNotNull();
    Assertions.assertThat(userFinded.getEmail()).isEqualTo(emailToBeSearched);
  }

  @Test
  @DisplayName("Must throw an exception when find by nonexisted email")
  void mustThrowAnExceptionWhenFindByNonexistentEmail() {
    String nonexistentEmail = "noexists@email.com";

    Assertions.assertThatExceptionOfType(UserNotFoundException.class)
        .isThrownBy(() -> userService.findByEmail(nonexistentEmail))
        .withMessage("User not found");
  }

}
