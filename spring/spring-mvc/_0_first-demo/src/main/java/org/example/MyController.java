package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// 过滤器拦截每一个请求
@Controller
public class MyController {
    @PostMapping("/first")
    @ResponseBody
    public String first(@RequestBody String body) {
        return body;
    }

}
