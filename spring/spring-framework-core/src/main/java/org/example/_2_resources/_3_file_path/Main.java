package org.example._2_resources._3_file_path;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) throws IOException {
        DefaultResourceLoader loader = new DefaultResourceLoader();
        //To load files from the application’s root directory folder, use the below template:
        Resource resource = loader.getResource("file:pom.xml");

        //To load files from the filesystem outside the application folder, use the absolute path of the file:
//        getResource("file:c:/temp/filesystemdata.txt");

        String result = IOUtils.toString(resource.getInputStream(), Charset.defaultCharset());
        Assert.assertTrue(result.length() > 0);
        System.out.println(result);
    }
}
