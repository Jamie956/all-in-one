package com.jamie.springcloud.controller;

import com.jamie.springcloud.util.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZJM
 * @date 2021/7/29 10:22
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    /**
     * 模拟登录服务，生成token
     * @return token
     */
    @GetMapping("/login")
    public String login() {
        String token = JwtUtil.generateToken(1L, "tom");
        System.out.println("token: " + token);
        return token;
    }
}
