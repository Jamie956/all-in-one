package org.example._2_resources._5_url_path;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) throws IOException {
        DefaultResourceLoader loader = new DefaultResourceLoader();
        //To load files from any URL, use the below template:
        Resource resource = loader.getResource("https://docs.spring.io/spring-framework/reference/core/resources.html");

        String result = IOUtils.toString(resource.getInputStream(), Charset.defaultCharset());
        System.out.println(result);
    }
}
