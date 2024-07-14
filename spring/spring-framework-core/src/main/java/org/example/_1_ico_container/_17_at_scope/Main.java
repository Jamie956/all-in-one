package org.example._1_ico_container._17_at_scope;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    /**
     * @Scope value 是 scope，表示 Bean 是单例还是多例
     */
    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(X.class);
        context.refresh();
        Assert.assertNotEquals(context.getBean(X.class), context.getBean(X.class));
    }
}
