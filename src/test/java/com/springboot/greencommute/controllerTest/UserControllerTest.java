package com.springboot.greencommute.controllerTest;

import com.springboot.greencommute.controllers.UserController;
import com.springboot.greencommute.dto.UserDto;
import com.springboot.greencommute.entities.User;
import com.springboot.greencommute.mapper.UserMapper;
import com.springboot.greencommute.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class UserControllerTest {

    @Mock
    private UserController userController;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserService userService;

    User user = new User(1,"test1",null);

    @Test
    void getUserByIdTest(){
        int id = 1;
        UserDto userDto = userMapper.toUserDto(user);
        Mockito.when(userController.getUserById(id)).thenReturn(userDto);
        Assertions.assertEquals(userDto,userController.getUserById(id));
        Mockito.verify(userController).getUserById(id);
    }
}
