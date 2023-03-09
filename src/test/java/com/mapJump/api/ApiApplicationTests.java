package com.mapJump.api;

import com.mapJump.api.user.model.CreateUserDto;
import com.mapJump.api.user.model.User;
import com.mapJump.api.user.UserRepository;
import com.mapJump.api.user.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@TestPropertySource("/application.properties")
@SpringBootTest
class ApiApplicationTests {

	@Autowired
	private JdbcTemplate jdbc;

	@Autowired
	private UserServiceImpl service;

	@Autowired
	private UserRepository repository;

	@BeforeEach
	public void setupDatabase() {
//		jdbc.execute("insert into user_details(id,username,password) values (1,'John Doe','toor')");
	}

	@AfterEach
	public void setupAfterTransaction() {
		jdbc.execute("DELETE FROM user_details");
	}

	@Test
	@DisplayName("Create user with correct data")
	public void createUserValidUser() {

		CreateUserDto newUser = CreateUserDto.builder().username("David").password("toor").build();

		service.createUser(newUser);

		User user = repository.findByUsername(newUser.getUsername());

		assertEquals(newUser.getUsername(),user.getUsername());

	}
	@Test
	@DisplayName("Create user without password")
	public void createUserWithoutPasswordInvalidUser() {

		CreateUserDto newUser = CreateUserDto.builder().username("Mark").password("").build();

		service.createUser(newUser);

		User user = repository.findByUsername(newUser.getUsername());

		assertEquals(null,user);

	}
	@Test
	@DisplayName("Create user without username")
	public void createUserWithoutUsernameInvalidUser() {

		CreateUserDto newUser = CreateUserDto.builder().username("").password("toor").build();

		service.createUser(newUser);

		User user = repository.findByUsername(newUser.getUsername());

		assertEquals(null,user);

	}
	@Test
	@DisplayName("Create user without both fields")
	public void createUserWithoutBothFieldsInvalidUser() {

		CreateUserDto newUser = CreateUserDto.builder().username("").password("").build();

		service.createUser(newUser);

		User user = repository.findByUsername(newUser.getUsername());

		assertEquals(null,user);

	}
	@Test
	@DisplayName("Can`t create user with same username")
	public void cantCreateUserWithTheSameUsername() {
		String username = "David";

		CreateUserDto user1 = CreateUserDto.builder().username(username).password("toor").build();
		CreateUserDto user2 = CreateUserDto.builder().username(username).password("toor").build();

		service.createUser(user1);
		service.createUser(user2);

		List<User> userList = repository.findAllByUsername(username);

		assertEquals(1,userList.size());

	}

	@Test
	@DisplayName("Get all users")
	public void getAllUsers() {
		CreateUserDto user1 = CreateUserDto.builder().username("Pepe").password("toor").build();
		CreateUserDto user2 = CreateUserDto.builder().username("David").password("toor").build();

		service.createUser(user1);
		service.createUser(user2);

		List<User> userList = repository.findAll();

		assertEquals(2,userList.size());

	}

}
