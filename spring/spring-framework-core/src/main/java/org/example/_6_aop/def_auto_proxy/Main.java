package org.example._6_aop.def_auto_proxy;

import org.example.share.A;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AopConfig.class, A.class);
        context.refresh();
        Assert.assertEquals(2, context.getBean(A.class).greeting(1));
    }
}
