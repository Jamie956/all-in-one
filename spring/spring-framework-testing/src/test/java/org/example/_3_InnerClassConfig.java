package org.example;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
public class _3_InnerClassConfig {

    @Configuration
    static class MyConfig {
        @Bean
        public X getX() {
            return new X();
        }
    }

    @Autowired
    private X x;

    @Autowired
    private ApplicationContext context;

    /**
     * 在测试类中定义 @Configuration 也是有效的
     */
    @Test
    public void test1() {
        Assert.assertNotNull(x);
        Assert.assertNotNull(context.getBean(X.class));
    }

}
