package tools;

/**
 * 使用 jdk21 jinfo
 * jinfo 查看 VM 参数
 * 查看用法：jinfo
 *
 * 设置 VM 参数 -Xms10m -Xmx10m
 * 启动 main
 * jps 查看进程 ID
 * 查看 VM 参数 ./jinfo -flags 22554
 * -XX:CICompilerCount=4 -XX:InitialHeapSize=10485760 -XX:MaxHeapSize=10485760 -XX:MaxNewSize=3145728 -XX:MinHeapDeltaBytes=524288 -XX:NewSize=3145728 -XX:OldSize=7340032 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseFastUnorderedTimeStamps -XX:+UseParallelGC
 *
 * 查看系统变量 ./jinfo -sysprops  22644
 * testKey=testValue
 * ...
 *
 */
public class JinfoTest {
    public static void main(String[] args) {
        System.setProperty("testKey", "testValue");
        while (true) {

        }
    }
}
