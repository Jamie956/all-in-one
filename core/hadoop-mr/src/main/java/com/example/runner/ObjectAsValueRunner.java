package com.example.runner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

/*
计算同一个手机号码的总流量

输入
136	1
136	1
137	2
137	3
138	2
139	1
140	2

输出
136	2...
137	5...
138	2...
139	1...
140	2...
*/
public class ObjectAsValueRunner extends AbstractRunner {
    @Test
    public void test() throws IOException {
        String path = "src/main/resources/input";
        loadInputData(path, "140\t2\n" +
                "136\t1\n" +
                "136\t1\n" +
                "137\t2\n" +
                "137\t3\n" +
                "138\t2\n" +
                "139\t1");

        new ObjectAsValueRunner().execute(path);
        String output = FileUtils.readFileToString(new File("src/main/resources/output/part-r-00000"), "UTF-8");
        Assert.assertTrue(output.contains("136\t[2]"));
    }

    @Override
    public void initJob() {
        initMapperReduceJob(ObjectAsValueRunner.class,
                FlowCountMapper.class, FlowCountReducer.class,
                Text.class, Counter.class,
                Text.class, Counter.class);
    }

    static class FlowCountMapper extends Mapper<LongWritable, Text, Text, Counter> {
        Text k = new Text();
        Counter v = new Counter();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] fields = line.split("\t");

            String phone = fields[0];
            int count = Integer.parseInt(fields[1]);

            k.set(phone);
            v.setCount(count);
            // 将电话号码一样的对象聚集在一起
            context.write(k, v);
        }
    }

    static class FlowCountReducer extends Reducer<Text, Counter, Text, Counter> {
        Counter v = new Counter();
        @Override
        protected void reduce(Text key, Iterable<Counter> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (Counter value : values) {
                sum += value.getCount();
            }
            v.setCount(sum);
            context.write(key, v);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Counter implements Writable {
        private int count;

        // 序列化方法
        @Override
        public void write(DataOutput out) throws IOException {
            out.writeInt(count);
        }

        // 反序列化方法
        @Override
        public void readFields(DataInput in) throws IOException {
            // 必须要求和序列化方法顺序一致
            count = in.readInt();
        }

        @Override
        public String toString() {
            return "[" + count + "]";
        }
    }
}
