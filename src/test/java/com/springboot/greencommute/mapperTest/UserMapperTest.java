package com.springboot.greencommute.mapperTest;

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
import java.util.Optional;

@SpringBootTest
class UserMapperTest {

    @Mock
    UserMapper userMapper;

    @Test
    void entityToDtoMapperTest(){
        User user = new User(1,"test1",null);
        UserDto userDto = new UserDto(1,"test1");
        Mockito.when(userMapper.toUserDto(user)).thenReturn(userDto);
        Assertions.assertEquals(userDto,userMapper.toUserDto(user));
        Mockito.verify(userMapper).toUserDto(user);
    }

    @Test
    void entityToDtoListMapperTest(){
        List<User> userList = new ArrayList<>();
        List<UserDto> userDtoList= new ArrayList<>();
        User user = new User(1,"test1",null);
        UserDto userDto = new UserDto(1,"test1");
        userList.add(user);
        userDtoList.add(userDto);
        Mockito.when(userMapper.toUserDtoList(userList)).thenReturn(userDtoList);
        Assertions.assertEquals(userDtoList,userMapper.toUserDtoList(userList));
        Mockito.verify(userMapper).toUserDtoList(userList);
    }
}
