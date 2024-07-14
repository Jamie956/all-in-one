package org.example._1_ico_container._18_at_async;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.Future;

public class Main {
    /**
     * @Async 注解的方法异步执行
     */
    @Test
    public void test() throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AsyncTask.class);
        context.refresh();

        AsyncTask task = context.getBean(AsyncTask.class);

        long start = System.currentTimeMillis();

        Future<String> t1 = task.doTaskOne();
        Future<String> t2 = task.doTaskTwo();
        Future<String> t3 = task.doTaskThree();

        while (true) {
            if (t1.isDone() && t2.isDone() && t3.isDone()) {
                break;
            }
            Thread.sleep(1000);
        }

        long end = System.currentTimeMillis();

        System.out.println("total spend -> " + (end - start) + "ms");
    }
}
