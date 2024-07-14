package org.example._1_ico_container._2_at_bean.demo1;

import org.example.share.EmptyObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    /**
     * @Bean 注解方法，它的返回对象注册到容器
     */
    @Test
    public void annotationBeanCreateReturnObject() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Config.class);
        context.refresh();
        Assert.assertNotNull(context.getBean(EmptyObject.class));
    }
}
