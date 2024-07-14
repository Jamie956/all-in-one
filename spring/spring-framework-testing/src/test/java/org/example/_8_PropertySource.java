package org.example;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(Z.class)
@TestPropertySource(properties = {"Timezone = GMT", "port = 4242"})
public class _8_PropertySource {

    @Autowired
    private Z z;

    /**
     * @TestPropertySource 定义测试 properties key-value
     */
    @Test
    public void test1() {
        Assert.assertEquals("GMT", z.getTimezone());
    }
}
