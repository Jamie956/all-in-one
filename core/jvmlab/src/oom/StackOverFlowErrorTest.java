package oom;

/**
 * javac -encoding UTF-8 oom/StackOverFlowErrorTest.java
 * java -Xss161k oom.StackOverFlowErrorTest
 *
 * 参数解释：
 * -Xss161k：限制栈内存空间
 */
public class StackOverFlowErrorTest {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        StackOverFlowErrorTest oom = new StackOverFlowErrorTest();
        try {
            oom.stackLeak();
        } catch (StackOverflowError e) {
            System.out.println("stack length: " + oom.stackLength);
            throw e;
        }
    }
}
