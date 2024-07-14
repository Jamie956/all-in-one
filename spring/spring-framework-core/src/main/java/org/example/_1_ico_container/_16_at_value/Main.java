package org.example._1_ico_container._16_at_value;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    /**
     * @Value value 是 properties key，如果 property 存在就给变量赋值
     */
    @Test
    public void readPropertiesValue() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(X.class);
        context.refresh();

        X bean = context.getBean(X.class);
        Assert.assertEquals("111", bean.getA());
        Assert.assertEquals("pureStringValue", bean.getB());
    }
}
