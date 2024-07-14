package com.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitTest {
	@Autowired
	private UserMapper UserMapper;

	@Before
	public void setup() {
		UserMapper.deleteAll();
		UserMapper.insert(new User(1L, "Jamie Zhou", UserSex.MAN, "Jamie"));
		UserMapper.insert(new User(2L, "Cat", UserSex.WOMAN, "Tomcat"));
	}

	@Test
	public void findAllTest() {
		List<User> users = UserMapper.getAll();
		Assert.assertEquals("Jamie Zhou", users.get(0).getUserName());
		Assert.assertEquals(2, users.size());
	}
	
	@Test
	public void getOneTest() {
		User user = UserMapper.getOne(1L);
		Assert.assertEquals("Jamie Zhou", user.getUserName());
	}
	
	@Test
	public void updateTest() {
		User user = UserMapper.getOne(1L);
		user.setUserName("Sam");
		user.setUserSex(UserSex.WOMAN);
		user.setNickName("s");
		UserMapper.update(user);
		User updatedUser = UserMapper.getOne(1L);
		Assert.assertEquals("Sam", updatedUser.getUserName());
	}
	
	@Test
	public void removeByIdTest() {
		UserMapper.delete(1L);
		List<User> users = UserMapper.getAll();
		Assert.assertEquals(1, users.size());
	}
}
