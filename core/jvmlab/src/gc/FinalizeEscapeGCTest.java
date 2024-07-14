package gc;

/**
 * 测试 finalize 是否可以执行多次?
 * 测试 finalize 方法清除对象后，重新引用对象，重新引用后是否会再次 finalize?
 * 结论：finalize 只执行一次
 */
public class FinalizeEscapeGCTest {
    public static FinalizeEscapeGCTest SAVE_HOOK = null;

    public static void isAlive(Object o) {
        if (o != null) {
            System.out.println("yes, i am still alive :)");
        } else {
            System.out.println("no, i am dead :(");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        //GC时重新指向实例
        FinalizeEscapeGCTest.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Throwable {
        SAVE_HOOK = new FinalizeEscapeGCTest();

        //对象设空，触发GC
        SAVE_HOOK = null;
        System.gc();
        //sleep 一会，等待调GC finalize
        Thread.sleep(500);
        // 对象在 final 被挽救了
        isAlive(SAVE_HOOK);

        SAVE_HOOK = null;
        //GC 不会再调 finalize
        System.gc();
        Thread.sleep(500);
    }
}