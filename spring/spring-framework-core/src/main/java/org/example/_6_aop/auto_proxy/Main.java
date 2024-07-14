package org.example._6_aop.auto_proxy;

import org.example.share.A;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    /**
     * Spring AOP
     * MethodInterceptor 即 advice，实现拦截的处理逻辑
     * BeanNameAutoProxyCreator 根据 Bean name 从容器获取 point cut 和 advice 并设置他们
     */
    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AopConfig.class, A.class);
        context.refresh();
        Assert.assertEquals(2, context.getBean(A.class).greeting(1));
    }
}
