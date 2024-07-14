package org.example;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class _5_ExtendContextConfiguration extends _4_ContextConfiguration {
    @Autowired
    private X x;

    /**
     * 继承的父类有 spring test 注解，子类也可以共享容器 beans
     */
    @Test
    public void test() {
        Assert.assertNotNull(x);
    }
}
