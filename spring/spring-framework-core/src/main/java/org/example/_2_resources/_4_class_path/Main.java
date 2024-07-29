package org.example._2_resources._4_class_path;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) throws IOException {
        //To load a file from the classpath, use the below template:
        DefaultResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource("classpath:org/example/_2_resources/_4_class_path/Main.class");

        String result = IOUtils.toString(resource.getInputStream(), Charset.defaultCharset());
        Assert.assertTrue(result.length() > 0);
        System.out.println(result);
    }

    @Test
    public void classpath() throws IOException {
        ClassPathResource resource = new ClassPathResource("org/example/_2_resources/_4_class_path/Main.class");
        String result = IOUtils.toString(resource.getInputStream(), Charset.defaultCharset());
        System.out.println(result);
    }
}
