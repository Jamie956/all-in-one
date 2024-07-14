package com.example.runner;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/*
 词频统计

 测试数据
 a b c
 v b
 a d a

 预期
 a	3
 b	2
 c	1
 d	1
 v	1
 */
public class WordCountRunner extends AbstractRunner {
    @Test
    public void test() throws IOException {
        String path = "src/main/resources/input";
        loadInputData(path, "a b c\n" +
                "v b\n" +
                "a d a");

        new WordCountRunner().execute(path);
        String output = FileUtils.readFileToString(new File("src/main/resources/output/part-r-00000"), "UTF-8");
        Assert.assertEquals("a\t3\n" +
                "b\t2\n" +
                "c\t1\n" +
                "d\t1\n" +
                "v\t1\n", output);
    }

    @Override
    public void initJob() {
        initMapperReduceJob(WordCountRunner.class,
                WordCountMapper.class, WordCountReducer.class,
                Text.class, IntWritable.class,
                Text.class, IntWritable.class);
    }

    // Mapper<KEYIN, VALUEIN, KEYOUT, VALUEOUT>
    // 在这个例子中，KEYIN LongWritable 表示输入文件每行首字符的 offset
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

