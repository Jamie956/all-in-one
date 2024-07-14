package oom;

import java.util.ArrayList;
import java.util.List;

/**
 * javac -encoding UTF-8 oom/HeapOutOfMemoryTest.java
 * java -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError oom.HeapOutOfMemoryTest
 *
 * 参数解释：
 * -Xms20m 堆初始内存
 * -Xmx20m 堆最大内存
 * -XX:+HeapDumpOnOutOfMemoryError 出现内存溢出异常时导出当前内存堆快照
 */
public class HeapOutOfMemoryTest {
    static class OOMObject{}
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
