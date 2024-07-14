package com.example.runner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/*
reduce 端压缩

输入
a b c
v b
a d a

输出
BZh91AY&SY�N�  ɀ 08 <    1 0 z����]��B@E:�
*/
public class ReduceCompressRunner extends AbstractRunner {
    @Test
    public void test() throws IOException {
        String path = "src/main/resources/input";
        loadInputData(path, "a b c\n" +
                "v b\n" +
                "a d a");

        new ReduceCompressRunner().execute(path);
        File file = new File("src/main/resources/output/part-r-00000.bz2");
        Assert.assertTrue(file.exists());
    }

    @Override
    public void initJob() {
        initMapperReduceJob(ReduceCompressRunner.class,
                WordCountMapper.class, WordCountReducer.class,
                Text.class, IntWritable.class,
                Text.class, IntWritable.class);
        // 设置reduce端输出压缩开启
        FileOutputFormat.setCompressOutput(job, true);
        // 设置压缩的方式 BZip2Codec/GzipCodec/DefaultCodec
        FileOutputFormat.setOutputCompressorClass(job, BZip2Codec.class);
    }

    static class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
        Text k = new Text();
        IntWritable v = new IntWritable(1);
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] words = line.split(" ");
            for (String word : words) {
                k.set(word);
                context.write(k, v);
            }
        }
    }

    static class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        IntWritable v = new IntWritable();
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable value : values) {
                sum += value.get();
            }
            v.set(sum);
            context.write(key, v);
        }
    }
}
