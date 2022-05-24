package br.com.emendes.transactionanalyzer.util;

import br.com.emendes.transactionanalyzer.controller.form.UpdateUserForm;

public class UpdateUserFormCreator {
  public static UpdateUserForm validUpdateUserForm() {
    return new UpdateUserForm("Lorem Sit", "lorem@email.com");
  }
}
