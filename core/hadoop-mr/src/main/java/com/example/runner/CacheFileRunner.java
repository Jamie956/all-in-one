package com.example.runner;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/*
 测试缓存文件

 输入
 order
 1001	1
 1002	2
 1003	3
 1004	1
 1005	2
 1006	3

 product
 1	小米
 2	华为
 3	格力

 输出
 1001	1	小米
 1002	2	华为
 1003	3	格力
 1004	1	小米
 1005	2	华为
 1006	3	格力
 */
public class CacheFileRunner extends AbstractRunner {
    @Test
    public void test() throws IOException {
        String path = "src/main/resources/input";
        loadInputData(path, "1001\t1\n" +
                "1002\t2\n" +
                "1003\t3\n" +
                "1004\t1\n" +
                "1005\t2\n" +
                "1006\t3");

        loadInputData("src/main/resources/input2", "1\t小米\n" +
                "2\t华为\n" +
                "3\t格力");

        new CacheFileRunner().execute(path);
        String output = FileUtils.readFileToString(new File("src/main/resources/output/part-m-00000"), "UTF-8");
        Assert.assertEquals(
                "1001\t1\t小米\n" +
                "1002\t2\t华为\n" +
                "1003\t3\t格力\n" +
                "1004\t1\t小米\n" +
                "1005\t2\t华为\n" +
                "1006\t3\t格力\n", output);
    }

    @Override
    public void initJob() {
        initMapperJob(CacheFileRunner.class, DistributedCacheMapper.class, Text.class, IntWritable.class);
        // 添加缓存文件
        String cacheFilePath = "src/main/resources/input2";
        try {
            job.addCacheFile(new URI(cacheFilePath));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    static class DistributedCacheMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
        HashMap<String, String> cacheMap = new HashMap<>();
        Text k = new Text();

        @Override
        protected void setup(Mapper<LongWritable, Text, Text, NullWritable>.Context context) throws IOException {
            // cache file product data
            URI[] cacheFiles = context.getCacheFiles();
            String path = cacheFiles[0].getPath();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));

            String line;
            while (StringUtils.isNotEmpty(line = reader.readLine())) {
                String[] fields = line.split("\t");

                String productId = fields[0];
                String productName = fields[1];
                cacheMap.put(productId, productName);
            }
            IOUtils.closeStream(reader);
        }

        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String productId = line.split("\t")[1];
            String productName = cacheMap.get(productId);
            k.set(line + "\t" + productName);

            context.write(k, NullWritable.get());
        }
    }

}
