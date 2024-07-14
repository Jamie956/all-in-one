package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
登录
http://localhost:8080/login

使用 admins authority
http://localhost:8080/test/index
成功返回

修改成其他 authority aaa
http://localhost:8080/test/index
返回403
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
