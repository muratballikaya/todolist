package com.ballikaya.todolist.service;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ballikaya.todolist.model.User;
import com.ballikaya.todolist.service.config.Config;
import com.ballikaya.todolist.service.config.TestConfig;
import com.ballikaya.todolist.service.service.UserService;

@ContextConfiguration(classes={Config.class, TestConfig.class})
@ActiveProfiles("test")
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

	@Autowired
	UserService userService;

	@PersistenceContext
	private EntityManager em;
	

	@Test
	public void saveAndRemove() {

		User user = new User();
		user.setUserName("testUserName");
		user.setName("testName");
		user.setSurname("testSurname");
		user.setMail("testMail");

		User savedUser = userService.saveUser(user);
		if(savedUser== null) assert(false);
		
		userService.remmoveByUserName(savedUser.getUserName());
		
		assert(true);
	}
	
	@Test
	public void getUser() {

		User user = new User();
		user.setUserName("testUserName");
		user.setName("testName");
		user.setSurname("testSurname");
		user.setMail("testMail");

		// create a new user
		User savedUser = userService.saveUser(user);
		if(savedUser== null) assert(false);
		
		// get user
		User userFetched= userService.getUser(savedUser.getUserName());
		
		// remove user that was inserted for test
		userService.remmoveByUserName(savedUser.getUserName());
		
		assertNotNull(userFetched);
	}

}
