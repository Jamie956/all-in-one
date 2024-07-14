package com.example.runner;

import com.example.Order;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/*
比较器分组过滤数据

计算每一个订单中最贵的商品
1 222
2 722
1 33
3 232
3 33
2 522
2 122

输出
1	222
2	722
3	232
*/
public class GroupComparatorRunner extends AbstractRunner {
    @Test
    public void test() throws IOException {
        String path = "src/main/resources/input";
        loadInputData(path, "1 222\n" +
                "2 722\n" +
                "1 33\n" +
                "3 232\n" +
                "3 33\n" +
                "3 44\n" +
                "2 522\n" +
                "2 122");

        new GroupComparatorRunner().execute(path);
        String output = FileUtils.readFileToString(new File("src/main/resources/output/part-r-00000"), "UTF-8");
        Assert.assertTrue(output.contains("1: 222"));
    }

    @Override
    public void initJob() {
        initMapperReduceJob(GroupComparatorRunner.class,
                OrderMapper.class, OrderReducer.class,
                Order.class, NullWritable.class,
                Order.class, NullWritable.class);
        job.setGroupingComparatorClass(MyGroupingComparator.class);
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

    static class MyGroupingComparator extends WritableComparator {
        protected MyGroupingComparator() {
            super(Order.class, true);
        }

        @Override
        public int compare(WritableComparable a, WritableComparable b) {
            if (((Order) a).getOrderId() != ((Order) b).getOrderId()) {
                return -1;
            }
            return 0;
        }
    }

}
