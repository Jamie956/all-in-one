package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
    @RequestMapping("/error")
    public String error() {
        System.out.println(1/0);
        return "index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

}
