package com.example.runner;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class OnlyMapperEtlRunner extends AbstractRunner {
    @Test
    public void test() throws IOException {
        String path = "src/main/resources/input";
        loadInputData(path, "K4u3KNo2bs8\tBrookers\t736\tNews & Politics\t207\t40268\t4.31\t587\t353\ta8Ye3cgVdhs\tN-bIscz79RU\tmrtnBl7GmWM\taYcIG0Kmjxs\tQUleM6nbvIw\tOeEL6BEOWKE\tFN9ZOOImjCg\tVdA1aCLRIXE\t-fTO_SYoFCM\tKNdj3ae2Tlk\tqRgnCnFKmZ4\tpgMV3qEsMdA\tgrpvone_ciY\tZ02gCksjA_o\tLF6j2Q2BQJc\t7Qs4pjPd6To\tySMvGJAT_9M\n" +
                "JL2oDOgnLyI\tHannahPL\t736\tEntertainment\t626\t19454\t4.83\t95\t43\tdqAnAm2-74o\txIm02hyPgPg\tySJli28DI2Q\tqdtlRUgxk8g\tgCJueoMmAjk\t0EsESnmGq4c\tO_G-OmCiJKg\t4LuE8W7p2jQ\tkI-H1fcK0uA\tG6nONTOK7J0\t7UHB0s-WYLc\t8MAdxnIRr-g\tcYFYQANIZ-U\tMbViMHC9Phc\ttOoK0RITPao\tatFLsl2vJFE\taCUEXPAaJec\tPgloiG9xaJc\tlW4jVDGwEdY\tBrj4oIT73kk");
        new OnlyMapperEtlRunner().execute(path);
        String output = FileUtils.readFileToString(new File("src/main/resources/output/part-m-00000"), "UTF-8");
        Assert.assertTrue(output.contains("News&Politics"));
    }

    @Override
    public void initJob() {
        initMapperJob(OnlyMapperEtlRunner.class,
                VideoMapper.class,
                NullWritable.class, Text.class);
    }

    static class VideoMapper extends Mapper<LongWritable, Text, NullWritable, Text> {
        Text v = new Text();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            line = etl(line);

            if (StringUtils.isBlank(line)) {
                return;
            }
            v.set(line);
            context.write(NullWritable.get(), v);
        }
    }

    public static String etl(String org) {
        String[] items = org.split("\t");
        if (items.length < 9) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        //去除空格 a & b -> a&b
        items[3] = items[3].replace(" ", "");
        for (int i = 0; i < items.length; i++) {
            if (i < 9) {
                //前面9个以\t分隔
                if (i == items.length - 1) {
                    sb.append(items[i]);
                } else {
                    sb.append(items[i]).append("\t");
                }
            } else {
                //处理相关id，以&连接
                if (i == items.length - 1) {
                    sb.append(items[i]);
                } else {
                    sb.append(items[i]).append("&");
                }
            }
        }

        return sb.toString();
    }
}
