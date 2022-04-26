package br.com.emendes.transactionanalyzer.model.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserForm {

  @NotEmpty
  @Size(min = 3, max = 20)
  private String name;

  private String email;

}
