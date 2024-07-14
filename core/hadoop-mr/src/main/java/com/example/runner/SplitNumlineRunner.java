package com.example.runner;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/*
任务切分
查看日志 2024-04-13 10:13:37,651 INFO [org.apache.hadoop.mapreduce.JobSubmitter] - number of splits:3

比如
输入文件一共9行
每个切片分3行
需要3个切片
 */
public class SplitNumlineRunner extends AbstractRunner {
    @Test
    public void test() throws IOException {
        String path = "src/main/resources/input";
        loadInputData(path, "a b c\n" +
                "v b\n" +
                "a d a");

        new SplitNumlineRunner().execute(path);
        String output = FileUtils.readFileToString(new File("src/main/resources/output/part-r-00000"), "UTF-8");
        Assert.assertTrue(output.contains("a"));
    }

    @Override
    public void initJob() {
        initMapperReduceJob(SplitNumlineRunner.class,
                WordCountMapper.class, WordCountReducer.class,
                Text.class, IntWritable.class,
                Text.class, IntWritable.class);

        // 设置每个切片 InputSplit 中划分三条记录
        NLineInputFormat.setNumLinesPerSplit(job, 3);
        // 使用 NLineInputFormat 处理记录数
        job.setInputFormatClass(NLineInputFormat.class);
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
