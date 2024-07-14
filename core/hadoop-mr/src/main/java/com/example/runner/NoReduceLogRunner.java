package com.example.runner;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class NoReduceLogRunner  extends AbstractRunner {
    @Test
    public void test() throws IOException {
        new NoReduceLogRunner().execute("src/main/resources/log");
        String output = FileUtils.readFileToString(new File("src/main/resources/output/part-m-00000"), "UTF-8");
        Assert.assertFalse(output.contains("58.215.204.118 - - [18/Sep/2013:06:51:41 +0000] \"-\" 400 0 \"-\" \"-\""));
    }

    @Override
    public void initJob() {
        initMapperJob(NoReduceLogRunner.class, LogMapper.class, Text.class, IntWritable.class);
    }

    static class LogMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
        Text k = new Text();
        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] fields = line.split(" ");
            if (fields.length < 11) {
                context.getCounter("map", "false").increment(1);
                return;
            }
            context.getCounter("map", "true").increment(1);
            k.set(line);
            context.write(k, NullWritable.get());
        }
    }

}
