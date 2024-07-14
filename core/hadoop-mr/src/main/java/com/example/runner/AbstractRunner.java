package com.example.runner;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class AbstractRunner implements Tool {
    protected Configuration conf = null;
    public static final String INPUT_PATH = "inputPath";
    public static final String OUTPUT_PATH = "outputPath";
    public Job job;

    @Override
    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    @Override
    public Configuration getConf() {
        return this.conf;
    }

    @Override
    public int run(String[] args) throws Exception {
        conf.set(INPUT_PATH, args[0]);
        conf.set(OUTPUT_PATH, args[1]);

        initConf();

        try {
            job = Job.getInstance(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }

        initJob();

        return job.waitForCompletion(true) ? 0 : 1;
    }

    public void initConf() {}

    public abstract void initJob();

    public void initMapperReduceJob(Class<?> runner,
                              Class<? extends Mapper> mapper,
                              Class<? extends Reducer> reducer,
                              Class<?> outKey1, Class<?> outValue1,
                              Class<?> outKey2, Class<?> outValue2) {
        job.setJarByClass(runner);
        job.setMapperClass(mapper);
        job.setReducerClass(reducer);

        job.setMapOutputKeyClass(outKey1);
        job.setMapOutputValueClass(outValue1);

        job.setOutputKeyClass(outKey2);
        job.setOutputValueClass(outValue2);

        setInputPath(job);
        setOutputPath(job);
    }

    public Job initMapperJob(Class<?> runner,
                                       Class<? extends Mapper> mapper,
                                       Class<?> outKey1, Class<?> outValue1) {
        job.setJarByClass(runner);
        job.setMapperClass(mapper);

        job.setMapOutputKeyClass(outKey1);
        job.setMapOutputValueClass(outValue1);
        // 或者
//        job.setOutputKeyClass(outKey1);
//        job.setOutputValueClass(outValue1);

        // 不做 reduce
        job.setNumReduceTasks(0);

        setInputPath(job);
        setOutputPath(job);

        return job;
    }

    public static void setInputPath(Job job) {
        Configuration conf = job.getConfiguration();
        try{
            FileSystem fs = FileSystem.get(conf);
            Path inputPath = new Path(conf.get(INPUT_PATH));
            if (fs.exists(inputPath)) {
                FileInputFormat.setInputPaths(job, inputPath);
            } else {
                throw new RuntimeException("HDFS 目录不存在" + conf.get("inputPath"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setOutputPath(Job job) {
        Configuration conf = job.getConfiguration();
        try {
            FileSystem fs = FileSystem.get(conf);
            Path outputPath = new Path(conf.get(OUTPUT_PATH));
            if (fs.exists(outputPath)) {
                fs.delete(outputPath, true);
            }
            FileOutputFormat.setOutputPath(job, outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void execute (String input) {
        String[] args = new String[]{input, "src/main/resources/output"};

        try {
            int code = ToolRunner.run(this, args);
            if (code == 0) {
                System.out.println("=============== Success ===============");
            } else {
                System.out.println("=============== Fail ===============");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void loadInputData(String path, String data) throws IOException {
        File inputFile = new File(path);
        if (inputFile.exists()) {
            inputFile.delete();
        }
        FileWriter fileWriter = new FileWriter(path);
        IOUtils.write(data, fileWriter);
        fileWriter.flush();
    }
}
