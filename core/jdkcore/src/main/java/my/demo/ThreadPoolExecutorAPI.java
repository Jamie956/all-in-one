package my.demo;

import org.junit.Test;

import java.util.concurrent.*;

public class ThreadPoolExecutorAPI {
    /**
     * workerCount < corePoolSize，新建一个核心线程执行当前任务
     */
    @Test
    public void poolInitTest() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());

        Runnable task = () -> {
            try {
                System.out.printf("thread:%s, pool:%s%n",
                        Thread.currentThread().getName(), pool.toString());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        //workerCount < corePoolSize，新建一个核心线程执行当前任务
        pool.execute(task);
        pool.shutdown();
    }

    /**
     * workerCount > corePoolSize，线程池RUNNING状态，任务加入阻塞队列
     */
    @Test
    public void poolAddQueueTest() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1));

        Runnable task = () -> {
            try {
                System.out.printf("thread:%s, pool:%s%n",
                        Thread.currentThread().getName(), pool.toString());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        pool.execute(task);
        //workerCount > corePoolSize，线程池RUNNING状态，任务加入阻塞队列
        pool.execute(task);
        pool.shutdown();
    }

    /**
     * workerCount > corePoolSize，阻塞队列满了，workerCount < maximumPoolSize 创建非核心线程执行任务
     */
    @Test
    public void poolMaxTest() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1));

        Runnable task = () -> {
            try {
                System.out.printf("thread:%s, pool:%s%n",
                        Thread.currentThread().getName(), pool.toString());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        pool.execute(task);
        pool.execute(task);
        //workerCount > corePoolSize，阻塞队列满了，workerCount < maximumPoolSize 创建非核心线程执行任务
        pool.execute(task);
        pool.shutdown();
    }

    /**
     * workerCount >= maximumPoolSize，阻塞队列满了，不能再创建新线程，采用拒绝策略
     */
    @Test
    public void poolRejectTest() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1),
                new ThreadPoolExecutor.AbortPolicy());

        Runnable task = () -> {
            try {
                System.out.printf("thread:%s, pool:%s%n",
                        Thread.currentThread().getName(), pool.toString());
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        pool.execute(task);
        pool.execute(task);
        //workerCount >= maximumPoolSize，阻塞队列满了，不能再创建新线程，采用拒绝策略
        pool.execute(task);
        pool.shutdown();
    }

    @Test
    public void fut() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                2, 3,
                1, TimeUnit.HOURS,
                new LinkedBlockingQueue<>(1),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        Callable<Integer> t = () -> {
            TimeUnit.SECONDS.sleep(2);
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            return sum;
        };

        FutureTask<Integer> futureTask = new FutureTask<>(t);
        pool.submit(futureTask);

        System.out.println(futureTask.get());
    }

}