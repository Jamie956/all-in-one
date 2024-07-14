package org.example._1_ico_container._11_at_import.demo3;

import org.example._1_ico_container._11_at_import.demo2.AppConfig;
import org.example.share.EmptyObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    /**
     * @Import value 是 ImportSelector 实现类时，ImportSelector.selectImports 返回值的路径的类注册到容器
     */
    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        Assert.assertNotNull(context.getBean(EmptyObject.class));
        Assert.assertNotNull(context.getBean(AppConfig.class));
    }
}