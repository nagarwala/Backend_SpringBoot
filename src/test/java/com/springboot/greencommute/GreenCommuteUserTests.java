package com.springboot.greencommute;

import com.springboot.greencommute.entities.User;
import com.springboot.greencommute.mapper.UserMapper;
import com.springboot.greencommute.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class GreenCommuteUserTests {

	@Mock
	private UserService userService;

	@Mock
	UserMapper userMapper;

	@Test
	 void getAllUserTest(){
		List<User> userList = Collections.emptyList();
		List<User> testUsers = userService.getUserList();
		Mockito.when(userService.getUserList()).thenReturn(userList);
		Assertions.assertEquals(userList,testUsers);
		Mockito.verify(userService).getUserList();
	}
	@Test
	 void getUserByIdTest(){
		int id = 1;
		User user = new User(1,"Nitesh",null);
		Optional<User> usersOptional = Optional.of(user);
		Mockito.when(userService.getUserById(id)).thenReturn(usersOptional);
		Assertions.assertEquals(usersOptional,userService.getUserById(id));
		Mockito.verify(userService).getUserById(id);
	}

}
