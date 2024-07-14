package org.example._1_ico_container._14_at_lazy;

import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    /**
     * @Lazy 注解 class，表示 Bean 在 get 的时候才会实例化
     */
    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(X.class);
        context.refresh();
        TestCase.assertFalse(context.getBeanFactory().containsSingleton("x"));
        context.getBean(X.class);
        TestCase.assertTrue(context.getBeanFactory().containsSingleton("x"));
    }
}
