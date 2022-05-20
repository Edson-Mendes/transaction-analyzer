package br.com.emendes.transactionanalyzer.util;

import br.com.emendes.transactionanalyzer.model.entity.Authority;
import br.com.emendes.transactionanalyzer.model.entity.User;

public class UserCreator {

  public static User validUser() {
    User user = User.builder()
        .id(1000l)
        .name("Lorem Ipsum")
        .email("lorem@email.com")
        .enabled(true)
        .password("123456")
        .build();

    user.addAuthority(new Authority("USER"));
    return user;
  }

  public static User disabledUser() {
    User user = User.builder()
        .id(1000l)
        .name("Lorem Ipsum")
        .email("lorem@email.com")
        .enabled(false)
        .password("123456")
        .build();

    user.addAuthority(new Authority("USER"));
    return user;
  }

  public static User updatedUser() {
    User user = User.builder()
        .id(1000l)
        .name("Lorem Sit")
        .email("lorem@email.com")
        .enabled(false)
        .password("123456")
        .build();

    user.addAuthority(new Authority("USER"));
    return user;
  }

}
