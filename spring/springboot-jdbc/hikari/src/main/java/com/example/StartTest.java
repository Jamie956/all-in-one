package com.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StartTest {
	@Autowired
	private UserDao userDao;

	private List<User> users;

	@Before
	public void init() {
		users = new ArrayList<>();
		User user1 = new User(1, "dog", String.valueOf(System.currentTimeMillis()));
		User user2 = new User(2, "deer", String.valueOf(System.currentTimeMillis()));
		users.add(user1);
		users.add(user2);

		userDao.cleanup();
		userDao.save(user1);
		userDao.save(user2);
	}

	@Test
	public void saveAndFindAllTest() {
		User user = new User(3, "cat", String.valueOf(System.currentTimeMillis()));
		userDao.save(user);

		List<User> users = userDao.findAll();
		Assert.assertEquals(3, users.size());
	}

	@Test
	public void findAllByIdtest() {
		User user = userDao.findAllById(1);
		Assert.assertEquals("dog", user.getName());
	}

}