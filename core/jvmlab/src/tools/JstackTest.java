package tools;

/**
 * 查看各个线程和状态
 * jstack 9628
 */
public class JstackTest {
    public static void main(String[] args) throws InterruptedException {
        int _1MB = 1024 * 1024;
        while (true) {
            Thread.sleep(500);
            byte[] bigSize = new byte[2 * _1MB];
        }
    }
}
