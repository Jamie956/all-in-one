package com.example.runner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.junit.Assert;
import org.junit.Test;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

/*
对象排序，MR 只在 cleanUp 方法输出结果

输入
java 1
html 2
php 1
spring 6
python 4
ruby 3

输出
spring	[6]
python	[4]
ruby	[3]
html	[2]
php	[1]
 */
public class CleanUpRunner extends AbstractRunner {
    @Test
    public void test() throws IOException {
        String path = "src/main/resources/input";
        loadInputData(path, "java 1\n" +
                "html 2\n" +
                "php 1\n" +
                "spring 6\n" +
                "python 4\n" +
                "ruby 3");
        new CleanUpRunner().execute(path);
        String output = FileUtils.readFileToString(new File("src/main/resources/output/part-r-00000"), "UTF-8");
        Assert.assertTrue(output.contains("spring\t[6]"));
    }

    @Override
    public void initJob() {
        initMapperReduceJob(CleanUpRunner.class,
                OrderCleanUpMapper.class, OrderCleanUpReducer.class,
                Counter.class, Text.class,
                Text.class, Counter.class);
    }

    static class OrderCleanUpMapper extends Mapper<LongWritable, Text, Counter, Text> {
        // 排序容器 TreeMap 使用 Counter compareTo 实现排序
        TreeMap<Counter, String> map = new TreeMap<>();
        Text v = new Text();

        @Override
        protected void map(LongWritable key, Text value, Context context) {
            String[] fields = value.toString().split(" ");

            String name = fields[0];
            int count = Integer.parseInt(fields[1]);
            Counter bean = new Counter(count);

            map.put(bean, name);
        }

        //cleanup 最后输出 Context
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            for (Counter bean : map.keySet()) {
                String name = map.get(bean);
                v.set(name);
                context.write(bean, v);
            }
        }
    }

    static class OrderCleanUpReducer extends Reducer<Counter, Text, Text, Counter> {
        TreeMap<Counter, Text> map = new TreeMap<>();
        Text k = new Text();

        @Override
        protected void reduce(Counter key, Iterable<Text> values, Context context) {
            Text name = values.iterator().next();
            Counter bean = new Counter(key.getCount());
            map.put(bean, new Text(name));
        }

        //最后 写出排序后的map
        @Override
        protected void cleanup(Reducer<Counter, Text, Text, Counter>.Context context) throws IOException, InterruptedException {
            for (Counter bean : map.keySet()) {
                k.set(map.get(bean));
                context.write(k, bean);
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
            return "[" + count + "]";
        }
    }
}
