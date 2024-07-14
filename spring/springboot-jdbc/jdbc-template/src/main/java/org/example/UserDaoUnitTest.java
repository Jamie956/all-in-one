package org.example;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoUnitTest {
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
		userDao.insertUsers(users);
	}

	@Test
	public void cleanupTest() {
		List<User> list1 = userDao.findAll();
		assertEquals(2, list1.size());

		userDao.cleanup();

		List<User> list2 = userDao.findAll();
		assertEquals(0, list2.size());
	}

	@Test
	public void saveTest() {
		User user = new User(3, "puppy", String.valueOf(System.currentTimeMillis()));
		userDao.save(user);

		List<User> list2 = userDao.findAll();
		assertEquals(3, list2.size());
	}

	@Test
	public void findAllTest() {
		List<User> list = userDao.findAll();
		assertEquals(2, list.size());
	}

	@Test
	public void removeById() {
		userDao.removeById(1);

		List<User> list = userDao.findAll();
		assertEquals(1, list.size());
	}
	
}