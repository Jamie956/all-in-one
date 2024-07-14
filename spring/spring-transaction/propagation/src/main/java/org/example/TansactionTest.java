package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class TansactionTest {
    @Autowired
    private User1Service user1Service;

    @Autowired
    private User2Service user2Service;

    @Test
    public void init() {
        user1Service.deleteAll();
        user2Service.deleteAll();
    }

    @Test
    public void success1_success2() {
        Assert.assertFalse(user1Service.list().isEmpty());
        Assert.assertFalse(user2Service.list().isEmpty());
    }

    @Test
    public void success1_fail2() {
        Assert.assertFalse(user1Service.list().isEmpty());
        Assert.assertTrue(user2Service.list().isEmpty());
    }

    @Test
    public void fail1_fail2() {
        Assert.assertTrue(user1Service.list().isEmpty());
        Assert.assertTrue(user2Service.list().isEmpty());
    }

    @Test
    public void fail1_success2() {
        Assert.assertTrue(user1Service.list().isEmpty());
        Assert.assertFalse(user2Service.list().isEmpty());
    }

    // ============================ PROPAGATION_REQUIRED ============================

    // 外部方法未开启事务，方法在各自的事务中独立运行，不受外部方法异常影响
    // user1 和 user2都插入成功
    @Test(expected = RuntimeException.class)
    public void require1() {
        user1Service.addRequired();
        user2Service.addRequired();
        throw new RuntimeException();
    }

    // 外部方法未开启事务，方法在各自的事务中独立运行
    // user1 成功，user2 异常回滚
    @Test(expected = RuntimeException.class)
    public void require2() {
        user1Service.addRequired();
        user2Service.addRequiredWithException();
    }

    // 外部方法开启事务，内部方法都加入外部事务，外部方法异常回滚，内部方法也回滚
    // user1 和 user2 异常回滚
    @Test(expected = RuntimeException.class)
    @Transactional(propagation = Propagation.REQUIRED)
    public void require3(){
        user1Service.addRequired();
        user2Service.addRequired();
        throw new RuntimeException();
    }

    // 外部方法开启事务，内部方法加入外部事务，内部方法回滚，外部方法整个事务回滚
    // user1 和 user2 异常回滚
    @Test(expected = RuntimeException.class)
    @Transactional(propagation = Propagation.REQUIRED)
    public void require4(){
        user1Service.addRequired();
        user2Service.addRequiredWithException();
    }

    // 外部方法开启事务，内部方法加入外部事务，即使外部方法捕获内部方法异常，外部方法整个事务回滚
    // user1 和 user2 异常回滚
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void require5(){
        user1Service.addRequired();
        try {
            user2Service.addRequiredWithException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ============================ PROPAGATION_REQUIRES_NEW ============================
    // 外部方法没有事务，内部方法在各自内部事务独立运行，内部方法不会回滚
    // user1 和 user2 成功插入
    @Test(expected = RuntimeException.class)
    public void require_new1() {
        user1Service.addRequiresNew();
        user2Service.addRequiresNew();
        throw new RuntimeException();
    }

    // 外部方法没有事务，内部方法在各自的事务
    // user1 成功执行，user2 回滚
    @Test(expected = RuntimeException.class)
    public void require_new2() {
        user1Service.addRequiresNew();
        user2Service.addRequiresNewWithException();
    }

    //外部方法开启事务，方法1加入外部方法事务，方法2在自的事务运行，外部事务异常，只有方法1回滚
    //1回滚 2成功
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void require_new3() {
        user1Service.addRequired();
        user2Service.addRequiresNew();
        throw new RuntimeException();
    }

    //外部方法开启事务，方法1加入外部方法事务，方法2在自己的事务运行，且抛出异常
    //外部方法有异常
    //方法1回滚 方法2回滚
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void require_new4() {
        user1Service.addRequired();
        user2Service.addRequiresNewWithException();
    }
}
