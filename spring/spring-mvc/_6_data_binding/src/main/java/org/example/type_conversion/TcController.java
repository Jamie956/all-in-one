package org.example.type_conversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class TcController {

    @Autowired
    ConversionService conversionService;

    @GetMapping("/df")
    public void df() {
        //default conversion
        System.out.println(conversionService.convert("25", Integer.class));
    }

    @GetMapping("/cs")
    public void cs() {
        Employee employee = conversionService.convert("1,50000.00", Employee.class);
        System.out.println(employee);
    }

    @GetMapping("/string-to-employee")
    public ResponseEntity<Object> getStringToEmployee(
            @RequestParam("employee") Employee employee) {
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/cf")
    public void cf() {
        Modes alpha = conversionService.convert("ALPHA", Modes.class);
        System.out.println(alpha);
    }

    @GetMapping("/gc")
    public void gc() {
        System.out.println(conversionService.convert(Integer.valueOf(11), BigDecimal.class));
        System.out.println(conversionService.convert(Double.valueOf(25.23), BigDecimal.class));
        System.out.println(conversionService.convert("2.32", BigDecimal.class));
    }
}