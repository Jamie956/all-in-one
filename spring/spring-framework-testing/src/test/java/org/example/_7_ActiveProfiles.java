package org.example;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ActiveProfiles("dev")
@SpringJUnitConfig(AppConfigWithProfile.class)
public class _7_ActiveProfiles {
    @Autowired
    ApplicationContext context;

    /**
     * @ActiveProfiles 与 @Profile 配套使用，只有 profile 一致才会创建 bean
     */
    @Test
    public void test1() {
        Assert.assertTrue(context.containsBean("getX"));
        Assert.assertFalse(context.containsBean("getY"));
    }
}