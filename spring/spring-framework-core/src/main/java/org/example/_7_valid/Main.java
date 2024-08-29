package org.example._7_valid;

import org.springframework.validation.BeanPropertyBindingResult;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        UserValidator validator = new UserValidator();
        if (validator.supports(User.class)) {
            BeanPropertyBindingResult errors = new BeanPropertyBindingResult(user, "user");
            validator.validate(user, errors);
            System.out.println(errors);
        }
    }
}
