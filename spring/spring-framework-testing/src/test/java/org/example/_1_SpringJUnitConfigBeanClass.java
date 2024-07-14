package org.example;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(X.class)
public class _1_SpringJUnitConfigBeanClass {
    @Autowired
    private X x;

    /**
     * @SpringJUnitConfig value 是 bean class，表示这个 class 注册到容器，而且可以注入当前测试类
     */
    @Test
    public void test1() {
        Assert.assertNotNull(x);
    }
}