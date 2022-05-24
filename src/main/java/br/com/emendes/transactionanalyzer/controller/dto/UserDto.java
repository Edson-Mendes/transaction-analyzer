package br.com.emendes.transactionanalyzer.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.emendes.transactionanalyzer.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

  private Long id;
  private String name;
  private String email;
  private Boolean enabled;

  public UserDto(User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.email = user.getEmail();
    this.enabled = user.getEnabled();

  }

  public static List<UserDto> fromUserList(List<User> users) {
    return users.stream().map(user -> new UserDto(user)).collect(Collectors.toList());
  }

}
