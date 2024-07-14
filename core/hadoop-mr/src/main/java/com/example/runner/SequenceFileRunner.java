package com.example.runner;

import com.example.WholeFileInputformat;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class SequenceFileRunner extends AbstractRunner {
    @Test
    public void test() throws IOException {
        String path = "src/main/resources/input";
        loadInputData(path, "a b c\n" +
                "v b\n" +
                "a d a");

        new SequenceFileRunner().execute(path);
        String output = FileUtils.readFileToString(new File("src/main/resources/output/part-r-00000"), "UTF-8");
        Assert.assertTrue(output.contains("BytesWritable"));
    }

    @Override
    public void initJob() {
        initMapperReduceJob(SequenceFileRunner.class,
                SequenceFileMapper.class, SequenceFileReducer.class,
                Text.class, BytesWritable.class,
                Text.class, BytesWritable.class);

        job.setInputFormatClass(WholeFileInputformat.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);
    }

    static class SequenceFileMapper extends Mapper<Text, BytesWritable, Text, BytesWritable> {
        @Override
        protected void map(Text key, BytesWritable value, Mapper<Text, BytesWritable, Text, BytesWritable>.Context context) throws IOException, InterruptedException {
            context.write(key, value);
        }
    }

    static class SequenceFileReducer extends Reducer<Text, BytesWritable, Text, BytesWritable> {
        @Override
        protected void reduce(Text key, Iterable<BytesWritable> values, Reducer<Text, BytesWritable, Text, BytesWritable>.Context context) throws IOException, InterruptedException {
            for (BytesWritable value : values) {
                context.write(key, value);
            }
        }
    }

}
