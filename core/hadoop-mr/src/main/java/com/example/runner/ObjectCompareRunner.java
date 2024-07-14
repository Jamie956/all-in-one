package com.example.runner;

import com.example.Order;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/*
WritableComparable 对象重写排序方法

1 222
2 722
1 33
3 232
3 33
2 522
2 122

输出
1,222
1,33
2,722
2,522
2,122
3,232
3,33
 */
public class ObjectCompareRunner extends AbstractRunner {
    @Test
    public void test() throws IOException {
        String path = "src/main/resources/input";
        loadInputData(path,
                "1 222\n" +
                "2 722\n" +
                "1 33\n" +
                "3 232\n" +
                "3 33\n" +
                "3 44\n" +
                "2 522\n" +
                "2 122");

        new ObjectCompareRunner().execute(path);
        String output = FileUtils.readFileToString(new File("src/main/resources/output/part-r-00000"), "UTF-8");
        Assert.assertTrue(output.contains("1: 222"));
    }

    @Override
    public void initJob() {
        initMapperReduceJob(ObjectCompareRunner.class,
                OrderMapper.class, OrderReducer.class,
                Order.class, NullWritable.class,
                Order.class, NullWritable.class);
    }

    static class OrderMapper extends Mapper<LongWritable, Text, Order, NullWritable> {
        Order k = new Order();
        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Order, NullWritable>.Context context) throws IOException, InterruptedException {
            String[] fields = value.toString().split(" ");
            int orderId = Integer.parseInt(fields[0]);
            int price = Integer.parseInt(fields[1]);
            k.setOrderId(orderId);
            k.setPrice(price);
            context.write(k, NullWritable.get());
        }
    }

    static class OrderReducer extends Reducer<Order, NullWritable, Order, NullWritable> {
        @Override
        protected void reduce(Order key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            context.write(key, NullWritable.get());
        }
    }

}
