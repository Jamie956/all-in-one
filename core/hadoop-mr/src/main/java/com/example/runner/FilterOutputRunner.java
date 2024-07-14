package com.example.runner;

import com.example.FilterOutputFormat;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/*
构建文件输出流，输出 MR 结果

输入
http://www.baidu.com
http://www.google.com
http://cn.bing.com
http://www.sohu.com
http://www.sohu.com
http://www.sina.com
http://www.sin2a.com
http://www.sin2desa.com
http://www.sindsafa.com

输出
baidu.log
http://www.baidu.com

other.log
http://cn.bing.com
http://www.google.com
http://www.sin2a.com
http://www.sin2desa.com
http://www.sina.com
http://www.sindsafa.com
http://www.sohu.com
 */
public class FilterOutputRunner extends AbstractRunner {
    @Test
    public void test() throws IOException {
        String path = "src/main/resources/input";
        loadInputData(path, "http://www.baidu.com\n" +
                "http://www.google.com\n" +
                "http://cn.bing.com\n" +
                "http://www.sohu.com\n" +
                "http://www.sohu.com\n" +
                "http://www.sina.com\n" +
                "http://www.sin2a.com\n" +
                "http://www.sin2desa.com\n" +
                "http://www.sindsafa.com");
        new FilterOutputRunner().execute(path);
        String output = FileUtils.readFileToString(new File("src/main/resources/output/baidu.log"), "UTF-8");
        Assert.assertTrue(output.contains("baidu"));
    }

    @Override
    public void initJob() {
        initMapperReduceJob(FilterOutputRunner.class,
                FilterMapper.class, FilterReducer.class,
                Text.class, NullWritable.class,
                Text.class, NullWritable.class);
        job.setOutputFormatClass(FilterOutputFormat.class);
    }

    static class FilterMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context) throws IOException, InterruptedException {
            context.write(value, NullWritable.get());
        }
    }

    static class FilterReducer extends Reducer<Text, NullWritable, Text, NullWritable> {
        Text k = new Text();

        @Override
        protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            String line = key.toString() + "\r\n";
            k.set(line);

            //不包括输出重复数据
            context.write(k, NullWritable.get());
        }
    }

}
