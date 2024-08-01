package org.example._3_validation._1_interface_validator;

import org.springframework.validation.FieldError;
import org.springframework.validation.MapBindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Person person = new Person();
        person.setAge(1000);

        Map<String, Object> map = new HashMap<>();
        MapBindingResult result = new MapBindingResult(map, "person");
        PersonValidator personValidator = new PersonValidator();
        personValidator.validate(person, result);

        List<FieldError> fieldErrors = result.getFieldErrors();
        fieldErrors.forEach(e -> {
            System.out.println(e.getField() + " - " + e.getCode());
        });
    }

}
