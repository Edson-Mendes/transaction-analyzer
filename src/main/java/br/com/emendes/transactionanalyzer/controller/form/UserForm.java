package br.com.emendes.transactionanalyzer.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.emendes.transactionanalyzer.model.entity.Authority;
import br.com.emendes.transactionanalyzer.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

  @NotBlank(message = "Name can not be blank")
  @Size(min = 3, max = 20, message = "Size name must be between 3 and 20")
  private String name;

  @NotBlank(message = "Email can not be blank")
  @Email(message = "Must be a valid email")
  private String email;

  public User toUser() {
    User user = User.builder()
        .name(this.name)
        .email(this.email)
        .enabled(true)
        .build();
    Authority userAuthority = new Authority("USER");
    user.addAuthority(userAuthority);
    return user;
  }
}
