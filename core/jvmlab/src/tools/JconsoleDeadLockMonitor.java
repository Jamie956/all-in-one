package tools;

public class JconsoleDeadLockMonitor {
    /**
     * 线程死锁等待演示
     * Jconsole -> 线程 -> 死锁
     */
    static class Runner implements Runnable {
        int a, b;

        public Runner(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)) {
                synchronized (Integer.valueOf(b)) {
                    System.out.println(a + b);
                }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runner(1, 2)).start();
            new Thread(new Runner(2, 1)).start();
        }
    }
}
