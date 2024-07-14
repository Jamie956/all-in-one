package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.Assert;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        A a = ctx.getBean(A.class);
        B b = ctx.getBean(B.class);
        Assert.notNull(a, "");
        Assert.notNull(b, "");
    }
}
