package com.example.runner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.junit.Assert;
import org.junit.Test;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;

/*
手机号136、137、138、139开头都分别放到一个独立的4个文件中，其他开头的放到一个文件中

136	1
136	1
137	2
137	3
138	2
139	1
140	2
 */
public class Partition2ManyFileRunner extends AbstractRunner {
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

        new Partition2ManyFileRunner().execute(path);
        String output1 = FileUtils.readFileToString(new File("src/main/resources/output/part-r-00000"), "UTF-8");
        Assert.assertTrue(output1.contains("136"));
        String output2 = FileUtils.readFileToString(new File("src/main/resources/output/part-r-00001"), "UTF-8");
        Assert.assertTrue(output2.contains("137"));
    }

    @Override
    public void initJob() {
        initMapperReduceJob(Partition2ManyFileRunner.class,
                FlowCountMapper.class, FlowCountReducer.class,
                Text.class, Counter.class,
                Text.class, Counter.class);

        // 自定义分区规则
        job.setPartitionerClass(MyPartitioner.class);
        // 指定reduce 数量
        job.setNumReduceTasks(5);
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
            //写出 key 电话号码，value 对象，将电话号码一样的对象聚集在一起
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

            //写出 key 电话号码，value 对象
            context.write(key, v);
        }

    }

    static class MyPartitioner extends Partitioner<Text, Counter> {
        @Override
        public int getPartition(Text key, Counter value, int numPartitions) {
            String phone = key.toString();

            if ("136".equals(phone)) {
                return 0;
            } else if ("137".equals(phone)) {
                return 1;
            } else if ("138".equals(phone)) {
                return 2;
            } else if ("139".equals(phone)) {
                return 3;
            } else {
                return 4;
            }
        }

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Counter implements WritableComparable<Counter> {
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
        public int compareTo(Counter bean) {
            if (this.count > bean.getCount()) {
                return -1;
            } else if (this.count < bean.getCount()) {
                return 1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return count + "...";
        }
    }
}
