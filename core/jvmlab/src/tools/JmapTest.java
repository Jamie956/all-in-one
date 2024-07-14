package tools;

/**
 * JDK 21 jmap
 * jmap：查看堆、导出堆快照、查看类加载器、查看等待回收的对象
 * 查看命令用法：jmap
 *
 * 启动 main 测试命令：
 * 查看进程ID jps
 * 查看堆各个对象数量和大小 ./jmap -histo 15814
 * 查看类加载器 ./jmap -clstats 15814
 * 导出堆快照 ./jmap -dump:live,format=b,file=heap.bin 16571
 * 分析导出快照 heap.bin：jvisualVM -> load heap.bin
 */
public class JmapTest {
    public static void main(String[] args) throws InterruptedException {
        int _1MB = 1024 * 1024;
        while (true) {
            Thread.sleep(500);
            byte[] bigSize = new byte[2 * _1MB];
        }
    }
}
