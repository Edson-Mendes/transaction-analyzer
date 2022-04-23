package br.com.emendes.transactionanalyzer.model.form;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SigninForm {

  @NotEmpty
  private String email;
  @NotEmpty
  private String password;

}
