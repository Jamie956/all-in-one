package org.example._2_resources._1_aware;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MyLoader.class);
        context.refresh();
        MyLoader bean = context.getBean(MyLoader.class);
        ResourceLoader resourceLoader = bean.getResourceLoader();
        Resource resource = resourceLoader.getResource("ioc-bean.xml");

        String result = IOUtils.toString(resource.getInputStream(), Charset.defaultCharset());
        Assert.assertTrue(result.length() > 0);
    }
}
