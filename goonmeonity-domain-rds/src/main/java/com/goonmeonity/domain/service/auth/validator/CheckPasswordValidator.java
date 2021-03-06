package com.goonmeonity.domain.service.auth.validator;

import com.goonmeonity.core.validator.ValidatorWithError;
import com.goonmeonity.domain.repository.user.UserRepository;
import com.goonmeonity.domain.service.auth.dto.InputPasswordAndRealPassword;
import com.goonmeonity.domain.service.auth.error.PasswordDoNotMatchError;

public class CheckPasswordValidator extends ValidatorWithError<InputPasswordAndRealPassword> {
  public CheckPasswordValidator() {
        super(new PasswordDoNotMatchError());
    }

    @Override
    public Boolean apply(InputPasswordAndRealPassword password) {
        return password.getInputPassword().equals(password.getRealPassword());
    }
}
