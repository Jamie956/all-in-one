package org.example;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitTest {
    @Resource
    private UserMapper userMapper;

    @Before
    public void setup() {
        userMapper.delete(Wrappers.emptyWrapper());
        userMapper.insert(new User(1L, "Jone", 18, "test1@baomidou.com"));
        userMapper.insert(new User(2L, "Jack", 20, "test2@baomidou.com"));
        userMapper.insert(new User(3L, "Tom", 28, "test3@baomidou.com"));
        userMapper.insert(new User(4L, "Sandy", 21, "test4@baomidou.com"));
    }

    @Test
    public void selectListTest() {
        List<User> users = userMapper.selectList(Wrappers.emptyWrapper());
        Assert.assertEquals("Jone", users.get(0).getName());
        Assert.assertEquals(4, users.size());
    }

    @Test
    public void customParamSqlTest() {
        List<User> users = userMapper.customParamSql("select * from user");
        Assert.assertEquals("Jone", users.get(0).getName());
        Assert.assertEquals(4, users.size());
    }
}
