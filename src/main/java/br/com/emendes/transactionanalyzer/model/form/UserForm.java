package br.com.emendes.transactionanalyzer.model.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
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

  @NotEmpty
  @Size(min = 3, max = 20)
  private String name;

  @NotEmpty
  @Email
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
