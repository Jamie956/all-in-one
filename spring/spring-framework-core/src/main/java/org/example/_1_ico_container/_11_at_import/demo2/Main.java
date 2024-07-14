package org.example._1_ico_container._11_at_import.demo2;

import org.example.share.EmptyObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    /**
     * @Import value 是 config class，表示将 config 和 它的 @Bean 注解方法的返回类型对象注册到容器
     */
    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        Assert.assertNotNull(context.getBean(TestConfig.class));
        Assert.assertNotNull(context.getBean(EmptyObject.class));
    }
}