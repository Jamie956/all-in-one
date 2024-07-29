package org.example._2_resources._7_autowire_loader;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(X.class);
        ctx.refresh();

        X bean = ctx.getBean(X.class);
        ResourceLoader loader = bean.loader;
        Resource resource = loader.getResource("file:pom.xml");
        System.out.println(IOUtils.toString(resource.getInputStream(), Charset.defaultCharset()));
    }
}
