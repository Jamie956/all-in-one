package org.example._3_validation._2_nest_valid;

import org.springframework.validation.FieldError;
import org.springframework.validation.MapBindingResult;

import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AddressValidator addressValidator = new AddressValidator();
        Address address = new Address();
        MapBindingResult result = new MapBindingResult(new HashMap<>(), "customer");

        CustomerValidator customerValidator = new CustomerValidator(addressValidator);
        Customer customer = new Customer();
        customer.setAddress(address);
        customerValidator.validate(customer, result);

        List<FieldError> fieldErrors = result.getFieldErrors();
        for (FieldError error : fieldErrors) {
            System.out.println(error.getCode());
        }
    }

}
