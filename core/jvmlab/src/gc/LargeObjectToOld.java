package gc;

/**
 * 测试超过阈值的对象直接放进老年代
 * cd path/to/src
 * javac -encoding UTF-8 gc/LargeObjectToOld.java
 * java -XX:+UseConcMarkSweepGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3000000 gc.LargeObjectToOld
 *
 * 内存使用情况：
 * Heap
 *  par new generation   total 9216K, used 835K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
 *   eden space 8192K,  10% used [0x00000007bec00000, 0x00000007becd0ec8, 0x00000007bf400000)
 *   from space 1024K,   0% used [0x00000007bf400000, 0x00000007bf400000, 0x00000007bf500000)
 *   to   space 1024K,   0% used [0x00000007bf500000, 0x00000007bf500000, 0x00000007bf600000)
 *  concurrent mark-sweep generation total 10240K, used 4096K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
 *  Metaspace       used 2535K, capacity 4486K, committed 4864K, reserved 1056768K
 *   class space    used 270K, capacity 386K, committed 512K, reserved 1048576K
 *
 * 参数解释：
 * -XX:+UseConcMarkSweepGC 使用 CMS GC
 * -Xms20M 堆初始内存
 * -Xmx20M 堆最大内存
 * -Xmn10M 新生代内存
 * -XX:+PrintGCDetails 打印堆内存使用情况
 * -XX:SurvivorRatio=8 eden, from, to 的分配比例 8:1:1
 * -XX:PretenureSizeThreshold=3000000 大于阈值的对象直接放老年代
 */
public class LargeObjectToOld {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        //直接分配在老年代中
        byte[] allocation = new byte[4 * _1MB];
    }
}
