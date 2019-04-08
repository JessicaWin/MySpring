package com.jessica.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springConfig.xml")
//@ContextConfiguration(classes = { Config.class })
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void testUpdate() {
		userService.updateUser("id", "test");
	}

	@Test
	public void testInsert() {
		userService.insertUser("test");
	}

}
