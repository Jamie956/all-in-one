package org.example;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= "/spring-config.xml")
public class _6_ContextConfigurationWithXml {
    @Autowired
    private X x;

    /**
     * 加载 xml 定义的 beans
     */
    @Test
    public void test() {
        Assert.assertNotNull(x);
    }
}
