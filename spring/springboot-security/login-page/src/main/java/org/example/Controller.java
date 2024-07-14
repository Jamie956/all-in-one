package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/hi")
    public String hi() {
        return "hi";
    }

    @GetMapping("/test/index")
    public String index() {
        return "index";
    }

    @GetMapping("/test/hi")
    public String testhi() {
        return "testhi";
    }
}
