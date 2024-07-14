package org.example;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/hi")
    // 资源与角色绑定
    @Secured({"ROLE_sale"})
    public String hi() {
        return "hi";
    }
}