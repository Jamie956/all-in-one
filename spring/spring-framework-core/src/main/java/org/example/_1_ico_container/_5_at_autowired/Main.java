package org.example._1_ico_container._5_at_autowired;

import org.example.share.EmptyObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    /**
     * @Autowired 注解 Bean 变量或者方法
     * 1.注解全局变量，表示如果容器中同类型的对象，注入给这个变量；
     * 2.注解setter，表示如果容器中同类型的对象，注入给 setter 方法入参对象
     */
    @Test
    public void test() {
        // Debug public static Set<BeanDefinitionHolder> registerAnnotationConfigProcessors(
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(EmptyObject.class, Y.class, Z.class);
        context.refresh();

        Assert.assertNotNull(context.getBean(EmptyObject.class));
        Assert.assertNotNull(context.getBean(Y.class));
        Assert.assertNotNull(context.getBean(Z.class));
        Assert.assertNotNull(context.getBean(Y.class).emptyObject);
        Assert.assertNotNull(context.getBean(Z.class).emptyObject);
    }
}