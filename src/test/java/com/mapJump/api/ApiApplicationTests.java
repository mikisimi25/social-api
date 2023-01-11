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

	Lecture thumbnail
0:07 / 3:16
		Course content
		Stop
		76. Testing Spring MVC Web Controllers - Overview
		3min
		Play
		77. Testing Spring MVC Web Controllers - Test Setup
		3min
		Play
		78. Testing Spring MVC Web Controllers - Test getStudentsHttpRequest
		5min
		Play
		79. Testing Spring MVC Web Controllers - Assert View Name
		3min
		Play
		80. Testing Spring MVC Web Controllers - Create Student - failing test
		8min
		Play
		81. Testing Spring MVC Web Controllers - Create Student - passing test
		5min
		Play
		82. Testing Spring MVC Web Controllers - Updates for UI Add/Get Students - Part 1
		5min
		Play
		83. Testing Spring MVC Web Controllers - Updates for UI Add/Get Students - Part 2
		4min
		Play
		84. Testing Spring MVC Web Controllers - Delete Student
		6min
		Play
		85. Testing Spring MVC Web Controllers - Error Page for Delete Student
		5min
		Search all course questions
		Search all course questions
		All questions in this course
		(226)
		AS
		How are assertIterableEquals and assertLinesMatch different from each other.
		Is it possible to replace one with other?@Test @DisplayName("Iterable Equals") void testIterableEquals() { List<String> theList = List.of("luv", "2", "code"); assertIterableEquals(theList,
		Is it possible to replace one with other?@Test @DisplayName("Iterable Equals") void testIterableEquals() { List<String> theList = List.of("luv", "2", "code"); assertIterableEquals(theList, demoUtils.getAcademyInList(), "Expected list should be same"); } @Test @DisplayName("Lines match") void testLinesMatch() { List<String> theList = List.of("luv", "2", "code"); assertLinesMatch(theList, demoUtils.getAcademyInList(), "Line should match"); }
		5 upvotes
		Anuj · Lecture 16 · 8 months ago
		AB
		At 6:47 - two @Tests are executed each containing 2 assertions. Doesn't this mean 4 tests were executed?
		I don't understand why only 2 tests were considered as executed. I mean, in a way I get it, it's because
		I don't understand why only 2 tests were considered as executed. I mean, in a way I get it, it's because @Test annotation was used above each method, but inside each method there are 2 assertions.What if in each @Test 1 assertions fails? Does mean that both tests failed? That they failed only halfway?Why not put each assertion in an independent @Test?Btw loved your Spring Boot course Chad, hope to see you more in this one, haha!
		3 upvotes
		Andrei · Lecture 6 · 8 months ago
		AS
		Correctly Testing
		I feel some confusion, I'm sorry but I have multiple questions:1- If I have a lot of controller and services
		I feel some confusion, I'm sorry but I have multiple questions:1- If I have a lot of controller and services classes, do I need to write @SpringBootTest on each class?2- I don't know which classes to make tests for them except that controller and service, are there any other classes than those to test them?3-  Can I use H2 database normally in ORM instead of JDBC ? I mean I can use spring profile to make 2 environments (testing & production), so I use h2 database during testing phase then after all tests passed, I will switch to production environment using real database such as mysql. (which is better and why?)4- when to use mock? I know it used for mocking dao and requests and responses, but in case of I'm inside controller class, I use it for service? and in case of I'm inside service, I use it for DAO ?
		2 upvotes
		Ahmed Magdy · Lecture 78 · 6 months ago
		JF
		Mocked test
		What is test created for where we assert already mocked value. Let's assume that doSomething() in class return null value
		What is test created for where we assert already mocked value. Let's assume that doSomething() in class return null value by deafult.when(doSomething()).thenReturn("something");assertEquals(doSomething(), "something");
		2 upvotes
		JustAnotherRandomGuy · Lecture 78 · 6 months ago
		MK
		Confusing method name
		Could I suggest calling the method checkIfStudentIsPresent as it seems very counterintuitive to call it checkIfStudentIsNull when it returns True if
		Could I suggest calling the method checkIfStudentIsPresent as it seems very counterintuitive to call it checkIfStudentIsNull when it returns True if the student is present (i.e. not Null)?
		3 upvotes
		Mark · Lecture 71 · 6 months ago
		Teach the world online
		Create an online video course, reach students across the globe, and earn money
		Top companies choose Udemy Business to build in-demand career skills.
		NasdaqVolkswagenBoxNetAppEventbrite
		© 2023 Udemy, Inc.
