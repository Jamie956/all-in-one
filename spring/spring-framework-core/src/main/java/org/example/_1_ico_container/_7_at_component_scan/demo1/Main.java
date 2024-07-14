package org.example._1_ico_container._7_at_component_scan.demo1;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    /**
     * @ComponentScan value 是包路径，表示扫描指定的包路径下的 Bean 并装载到容器
     */
    @Test
    public void test() {
        //register internal post processor bean definition
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Config.class);
        context.refresh();

        Assert.assertNotNull(context.getBean(X.class));
    }
}
