package br.com.emendes.transactionanalyzer.util;

import br.com.emendes.transactionanalyzer.model.form.UserForm;

public class UserFormCreator {

  public static UserForm validUserForm() {
    return new UserForm("Lorem Ipsum", "lorem@email.com");
  }

}
