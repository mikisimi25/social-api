package com.mapJump.api;

import com.mapJump.api.user.CreateUserDto;
import com.mapJump.api.user.User;
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

		assertEquals(newUser.getUsername(),user.getUsername(),"find by username");

	}
	@Test
	@DisplayName("Create user without password")
	public void createUserWithoutPasswordInvalidUser() {

		CreateUserDto newUser = CreateUserDto.builder().username("Mark").password("").build();

		service.createUser(newUser);

		User user = repository.findByUsername(newUser.getUsername());

		assertEquals(null,user,"find by username");

	}
	@Test
	@DisplayName("Create user without username")
	public void createUserWithoutUsernameInvalidUser() {

		CreateUserDto newUser = CreateUserDto.builder().username("").password("toor").build();

		service.createUser(newUser);

		User user = repository.findByUsername(newUser.getUsername());

		assertEquals(null,user,"find by username");

	}
	@Test
	@DisplayName("Create user without both fields")
	public void createUserWithoutBothFieldsInvalidUser() {

		CreateUserDto newUser = CreateUserDto.builder().username("").password("").build();

		service.createUser(newUser);

		User user = repository.findByUsername(newUser.getUsername());

		assertEquals(null,user,"find by username");

	}

}
