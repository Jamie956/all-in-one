package org.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StartTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void init() {
        userRepository.deleteAll();
        for (int i = 1; i < 10; i++) {
            User u = userRepository.save(new User((long) i, "json" + i, "json" + i + "@gmail.com"));
        }
    }

    @Test
    public void saveTest() {
        User user = userRepository.save(new User(3L, "cat", "cat@gmail.com"));
        Assert.assertEquals("cat", user.getName());
    }

    @Test
    public void findAllTest() {
        List<User> all = userRepository.findAll();
        Assert.assertEquals(9, all.size());

        Page<User> pages = userRepository.findAll(PageRequest.of(1, 3, Sort.DEFAULT_DIRECTION));
        Assert.assertEquals(3, pages.getSize());
    }

    @Test
    public void findByNameTest() {
        List<User> user = userRepository.findByName("json1");
        Assert.assertEquals("json1", user.get(0).getName());
    }

    @Test
    public void nativeQueryByIdTest() {
        User user = userRepository.nativeQueryById(1);
        Assert.assertEquals("json1", user.getName());
    }

    @Test
    public void paramQueryByIdTest() {
        User user = userRepository.paramQueryById(1L);
        Assert.assertEquals("json1", user.getName());
    }

    @Test
    public void notNativeQueryByIdTest() {
        User user = userRepository.notNativeQueryById(1L);
        Assert.assertEquals("json1", user.getName());
    }

    @Test
    public void nameNativeQueryById() {
        User user = userRepository.nameNativeQueryById(1);
        Assert.assertEquals("json1", user.getName());
    }

}
