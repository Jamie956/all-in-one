package org.example;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class _4_ContextConfiguration {
    @Autowired
    private X x;

    /**
     * @ExtendWith(SpringExtension.class)
     * @ContextConfiguration(classes = AppConfig.class)
     * 相当于 @SpringJUnitConfig(AppConfig.class)
     */
    @Test
    public void test() {
        Assert.assertNotNull(x);
    }
}