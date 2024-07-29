package org.example._2_resources._6_at_value;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.Charset;


public class Main {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(X.class);
        ctx.refresh();

        X bean = ctx.getBean(X.class);
        Resource resource = bean.resource;
        String result = IOUtils.toString(resource.getInputStream(), Charset.defaultCharset());
        System.out.println(result);
    }
}
