package br.com.emendes.transactionanalyzer.model.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.emendes.transactionanalyzer.model.User;
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
    return User.builder()
        .name(this.name)
        .email(this.email)
        .build();
  }
}
