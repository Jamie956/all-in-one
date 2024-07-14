package com.example.runner;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/*
自动分割输入数据

输入
java#a
html#b
c#php
d spring
python e
ruby f
g h

输出
c	1
d spring	1
g h	1
html	1
java	1
python e	1
ruby f	1
*/
public class KvSeperatorRunner extends AbstractRunner {
    @Test
    public void test() throws IOException {
        String path = "src/main/resources/input";
        loadInputData(path, "java#a\n" +
                "html#b\n" +
                "c#php\n" +
                "d spring\n" +
                "python e\n" +
                "ruby f\n" +
                "g h");

        new KvSeperatorRunner().execute(path);
        String output = FileUtils.readFileToString(new File("src/main/resources/output/part-r-00000"), "UTF-8");
        Assert.assertTrue(output.contains("c\t1"));
    }

    @Override
    public void initConf() {
        //配置指定 kv 分隔符
        conf.set(KeyValueLineRecordReader.KEY_VALUE_SEPERATOR, "#");
    }

    @Override
    public void initJob() {
        initMapperReduceJob(KvSeperatorRunner.class,
                KVTextMapper.class, KVTextReducer.class,
                Text.class, IntWritable.class,
                Text.class, IntWritable.class);

        job.setInputFormatClass(KeyValueTextInputFormat.class);
    }

    static class KVTextMapper extends Mapper<Text, Text, Text, IntWritable> {
        IntWritable v = new IntWritable(1);

        @Override
        protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
            //key 不再是首字符 offset，而是分割后的 key
            context.write(key, v);
        }
    }

    static class KVTextReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        IntWritable v = new IntWritable();

        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable value : values) {
                sum += value.get();
            }
            v.set(sum);
            context.write(key, v);
        }
    }

}
