package gc;

/**
 * 测试对象回收是否使用引用计数法
 * -XX:+PrintGCDetails
 * 查看GC日志：发生了full GC，对象被回收
 * 结论：在对象互相引用的情况下，引用计数不为0，从GC日志看出对象已经被回收，所以对象不是使用引用计数算法
 */
public class ReferenceCountingGC {
    public Object instance = null;
    private static final int _1MB = 1024 * 1024;
    private byte[] bigSize = new byte[2 * _1MB];

    public static void main(String[] args) {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;
        // 假设在这行发生GC， objA和objB是否能被回收？
        System.gc();
    }
}