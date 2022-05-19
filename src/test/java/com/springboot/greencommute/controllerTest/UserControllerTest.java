package com.springboot.greencommute.controllerTest;

import com.springboot.greencommute.controllers.UserController;
import com.springboot.greencommute.dto.UserDto;
import com.springboot.greencommute.entities.User;
import com.springboot.greencommute.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserControllerTest {

    @Mock
    UserController userController;

    @Mock
    UserMapper userMapper;

    User user = new User(1,"test1",null);

    @Test
    void getUserListTest(){
        List<User> userList = new ArrayList<>();
        userList.add(user);
        List<UserDto> userDtoList = userMapper.toUserDtoList(userList);
        Mockito.when(userController.getListOfUsers()).thenReturn(userDtoList);
        Assertions.assertEquals(userDtoList,userController.getListOfUsers());
        Mockito.verify(userController).getListOfUsers();
    }

    @Test
    void getUserByIdTest(){
        int id = 1;
        UserDto userDto = userMapper.toUserDto(user);
        Mockito.when(userController.getUserById(id)).thenReturn(userDto);
        Assertions.assertEquals(userDto,userController.getUserById(id));
        Mockito.verify(userController).getUserById(id);
    }
}
