package org.example._6_aop.xml;

import org.example.share.A;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    /**
     * ProxyFactoryBean 设置代理目标和切面
     */
    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        context.setConfigLocation("spring-aop.xml");
        context.refresh();
        Assert.assertEquals(2, ((A) context.getBean("haloProxy")).greeting(1));
    }
}