package org.example._1_ico_container._15_at_conditional;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    /**
     * @Conditional value 是 Condition 的实现类，Condition.matches 返回的 boolean 决定 Bean 能不能注册到容器
     */
    @Test(expected = NoSuchBeanDefinitionException.class)
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(X.class);
        context.refresh();
        Assert.assertNotNull(context.getBean(X.class));
    }
}
