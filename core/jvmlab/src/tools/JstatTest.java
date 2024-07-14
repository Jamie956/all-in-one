package tools;

/**
 * jstat 主要查看 GC 状况
 * command usage: jstat -h
 * 查看有哪些 option：jstat -options
 *
 * start main
 * jps get pid
 * 查看GC：jstat -gc 23368
 * jstat -gc 2764 250 20：每250 毫秒查询一次GC，一共查询20次
 *
 * header 解释 https://blog.csdn.net/zhaozheng7758/article/details/8623549
 *
 */
public class JstatTest {
    public static void main(String[] args) throws InterruptedException {
        int _1MB = 1024 * 1024;
        while (true) {
            Thread.sleep(500);
            byte[] bigSize = new byte[2 * _1MB];
        }
    }
}
