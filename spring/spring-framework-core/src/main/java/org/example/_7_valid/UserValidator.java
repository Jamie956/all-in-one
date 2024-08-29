package org.example._7_valid;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (StringUtils.isEmpty(user.getName())) {
            errors.rejectValue("name", "name.required", "Name cannot be empty");
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            errors.rejectValue("email", "email.required", "Invalid email format");
        }
    }
}
