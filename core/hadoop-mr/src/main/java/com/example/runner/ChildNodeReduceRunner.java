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
 各节点自身进行reduce，减少reduce 阶段的处理数据量，减少网络IO
 map -> combine -> reduce

输入
 a b c
 v b
 a d a

输出
 a	3
 b	2
 c	1
 d	1
 v	1
 */
public class ChildNodeReduceRunner extends AbstractRunner {
    @Test
    public void test() throws IOException {
        String path = "src/main/resources/input";
        loadInputData(path, "a b c\n" +
                "v b\n" +
                "a d a");

        new ChildNodeReduceRunner().execute(path);
        String output = FileUtils.readFileToString(new File("src/main/resources/output/part-r-00000"), "UTF-8");
        Assert.assertTrue(output.contains("a\t3"));
    }

    @Override
    public void initJob() {
        initMapperReduceJob(ChildNodeReduceRunner.class,
                WordCountMapper.class, WordCountReducer.class,
                Text.class, IntWritable.class,
                Text.class, IntWritable.class);
        job.setCombinerClass(WordCountCombiner.class);
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

    static class WordCountCombiner extends Reducer<Text, IntWritable, Text, IntWritable> {
        IntWritable v = new IntWritable();
        //各节点自身做 reduce
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
