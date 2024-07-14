package org.example;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/hi")
    // post 会执行方法体，即使没有权限
    @PostAuthorize("hasAnyAuthority('admins')")
    public String hi() {
        System.out.println("执行方法体");
        return "hi";
    }
}