package com.casystems.caspracticaltest.security.validators;

import com.casystems.caspracticaltest.security.services.UserDetailsServiceImpl;
import com.casystems.caspracticaltest.system.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserDetailsServiceImpl userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 5 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.user.username");
        }
        try{
            if (userService.findByUsername(user.getUsername()) != null) {
                errors.rejectValue("username", "Duplicate.user.username");
            }
        }catch (Exception ex){}


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 5 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.user.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.user.passwordConfirm");
        }
        try {
            if (userService.findByEmail(user.getEmail()) != null) {
                errors.rejectValue("email", "Email");
            }
        }catch (Exception ex){}
    }
}