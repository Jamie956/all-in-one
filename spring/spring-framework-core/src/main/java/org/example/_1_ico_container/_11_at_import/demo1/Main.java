package org.example._1_ico_container._11_at_import.demo1;

import org.example.share.EmptyObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    /**
     * @Import value 是 class，表示将 class Bean 注册到容器
     */
    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        Assert.assertNotNull(context.getBean(EmptyObject.class));
    }
}