package gc;

/**
 * 测试新生代 Minor GC
 * 内存分配：新生代 10MB，老年代 10MB
 *
 * 测试步骤：
 * cd path/to/src
 * javac -encoding UTF-8 gc/DoMinorGC.java
 * java -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 gc.DoMinorGC
 *
 * 测试结果：
 * allocation1, allocation2, allocation3, allocation4 都为空
 * Heap
 *  PSYoungGen      total 9216K, used 835K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
 *   eden space 8192K, 10% used [0x00000007bf600000,0x00000007bf6d0e98,0x00000007bfe00000)
 *   from space 1024K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007c0000000)
 *   to   space 1024K, 0% used [0x00000007bfe00000,0x00000007bfe00000,0x00000007bff00000)
 *  ParOldGen       total 10240K, used 0K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
 *   object space 10240K, 0% used [0x00000007bec00000,0x00000007bec00000,0x00000007bf600000)
 *  Metaspace       used 2535K, capacity 4486K, committed 4864K, reserved 1056768K
 *   class space    used 270K, capacity 386K, committed 512K, reserved 1048576K
 *
 * 仅分配 allocation1, allocation2, allocation3 的内存
 * Heap
 *  PSYoungGen      total 9216K, used 6979K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
 *   eden space 8192K, 85% used [0x00000007bf600000,0x00000007bfcd0ec8,0x00000007bfe00000)
 *   from space 1024K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007c0000000)
 *   to   space 1024K, 0% used [0x00000007bfe00000,0x00000007bfe00000,0x00000007bff00000)
 *  ParOldGen       total 10240K, used 0K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
 *   object space 10240K, 0% used [0x00000007bec00000,0x00000007bec00000,0x00000007bf600000)
 *  Metaspace       used 2535K, capacity 4486K, committed 4864K, reserved 1056768K
 *   class space    used 270K, capacity 386K, committed 512K, reserved 1048576K
 *
 * 给 allocation1, allocation2, allocation3, allocation4 分配内存，由于年轻代放不下对象 allocation4，直接放到老年代
 * Heap
 *  PSYoungGen      total 9216K, used 6979K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
 *   eden space 8192K, 85% used [0x00000007bf600000,0x00000007bfcd0ec8,0x00000007bfe00000)
 *   from space 1024K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007c0000000)
 *   to   space 1024K, 0% used [0x00000007bfe00000,0x00000007bfe00000,0x00000007bff00000)
 *  ParOldGen       total 10240K, used 4096K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
 *   object space 10240K, 40% used [0x00000007bec00000,0x00000007bf000010,0x00000007bf600000)
 *  Metaspace       used 2535K, capacity 4486K, committed 4864K, reserved 1056768K
 *   class space    used 270K, capacity 386K, committed 512K, reserved 1048576K
 *
 * 参数解释：
 * -Xms20M 堆初始化大小
 * -Xmx20M 堆最大容量
 * -Xmn10M 堆新生代的大小
 * -XX:+PrintGCDetails 打印堆各分区内存使用情况
 * -XX:SurvivorRatio=8 表示年轻代分区 Eden, From, To 的内存分配占比 8:1:1
 */
public class DoMinorGC {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        // 出现一次Minor GC
        allocation4 = new byte[4 * _1MB];
    }
}
