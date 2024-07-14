package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.Assert;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        HaloService halo = ctx.getBean(HaloService.class);

        Assert.state("aa".equals(halo.getHaloProperties().getPrefix()), "");
        Assert.state("bb".equals(halo.getHaloProperties().getSuffix()), "");
    }
}
