package br.com.emendes.transactionanalyzer.util;

import br.com.emendes.transactionanalyzer.controller.dto.UserDto;

public class UserDtoCreator {
  public static UserDto validUserDto() {
    return new UserDto(UserCreator.validUser());
  }
}
