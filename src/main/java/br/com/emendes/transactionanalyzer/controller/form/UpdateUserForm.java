package br.com.emendes.transactionanalyzer.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserForm {

  @NotBlank(message = "Name can not be blank")
  @Size(min = 3, max = 20, message = "Size name must be between 3 and 20")
  private String name;

  private String email;

}
