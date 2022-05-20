package br.com.emendes.transactionanalyzer.util;

import br.com.emendes.transactionanalyzer.model.dto.UserDto;

public class UserDtoCreator {
  public static UserDto validUserDto() {
    return new UserDto(UserCreator.validUser());
  }
}
