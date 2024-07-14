package com.example.runner;

import com.example.Table;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/*
合并两个表

1001	1
1002	2
1003	3
1004	1
1005	2
1006	3

1	小米
2	华为
3	格力

输出
1004	1	小米
1001	1	小米
1005	2	华为
1002	2	华为
1006	3	格力
1003	3	格力
 */
public class JoinTableRunner extends AbstractRunner {
    @Test
    public void test() throws IOException {
        new JoinTableRunner().execute("src/main/resources/table");
        String output = FileUtils.readFileToString(new File("src/main/resources/output/part-r-00000"), "UTF-8");
        Assert.assertTrue(output.contains("1004\t1\t小米"));
    }

    @Override
    public void initJob() {
        initMapperReduceJob(this.getClass(),
                TableMapper.class, TableReducer.class,
                Text.class, Table.class,
                Table.class, NullWritable.class);
    }

    static class TableMapper extends Mapper<LongWritable, Text, Text, Table> {
        String fileName;
        Table table = new Table();
        Text k = new Text();

        @Override
        protected void setup(Mapper<LongWritable, Text, Text, Table>.Context context) {
            // 获取文件的名称
            FileSplit inputSplit = (FileSplit) context.getInputSplit();
            fileName = inputSplit.getPath().getName();
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] fields = value.toString().split("\t");

            //用关联的id 作为key，使得相同key 聚合在一起
            if (fileName.startsWith("order")) {
                String orderId = fields[0];
                String productId = fields[1];

                table.setOrderId(orderId);
                table.setProductId(productId);
                table.setProductName("");
                table.setFlag("order");

                //productId 作为key，聚合
                k.set(productId);
            } else if (fileName.startsWith("product")) {
                String productId = fields[0];
                String productName = fields[1];

                table.setOrderId("");
                table.setProductId(productId);
                table.setProductName(productName);
                table.setFlag("product");

                //productId 作为key，聚合
                k.set(productId);
            }

            context.write(k, table);
        }
    }

    static class TableReducer extends Reducer<Text, Table, Table, NullWritable> {
        @Override
        protected void reduce(Text key, Iterable<Table> values, Context context) throws IOException, InterruptedException {
            ArrayList<Table> orders = new ArrayList<>();
            Table product = new Table();

            for (Table value : values) {
                String flag = value.getFlag();
                String orderId = value.getOrderId();
                String productId = value.getProductId();
                String productName = value.getProductName();

                if ("order".equals(flag)) {
                    Table order = new Table();
                    order.setOrderId(orderId);
                    order.setProductId(productId);
                    orders.add(order);
                } else if ("product".equals(flag)) {
                    product.setProductId(productId);
                    product.setProductName(productName);
                }
            }

            for (Table order : orders) {
                order.setProductName(product.getProductName());
                context.write(order, NullWritable.get());
            }
        }
    }

}
