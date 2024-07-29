package org.example._2_resources._2_ctx_resource;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        Resource resource = context.getResource("ioc-bean.xml");

        String result = IOUtils.toString(resource.getInputStream(), Charset.defaultCharset());
        Assert.assertTrue(result.length() > 0);
    }
}
