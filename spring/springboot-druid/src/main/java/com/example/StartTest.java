package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.User;
import com.example.UserRepository;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StartTest {
	
	@Autowired
	private UserRepository userRepository;

	@Test
	public void save() {
		for (int i = 1; i < 10; i++) {
			User u = userRepository.save(new User("json", "json" +i+ "@gmail.com"));
		}
	}

	@Test
	public void findAll() {
		List<User> us = userRepository.findAll();
	}
	
}
