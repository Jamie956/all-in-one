package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// 过滤器拦截每一个请求
@Controller
public class MyController {
    @PostMapping("/b")
    @ResponseBody
    public String b(@RequestBody String body) {
        return body;
    }

    @GetMapping("/a")
    @ResponseBody
    public String a() {
        return "ack";
    }
}
