package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
    @RequestMapping("/test")
    @ResponseBody
    public String index() {
        System.out.println("------> controller");
        return "ok";
    }
}
