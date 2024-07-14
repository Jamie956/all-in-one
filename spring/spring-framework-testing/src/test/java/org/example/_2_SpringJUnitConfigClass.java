package org.example;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(AppConfig.class)
public class _2_SpringJUnitConfigClass {
    @Autowired
    private X x;

    /**
     * @SpringJUnitConfig value 是 config class，表示这个 class 和 config beans 注册到容器，而且可以注入当前测试类
     */
    @Test
    public void test() {
        Assert.assertNotNull(x);
    }
}