package com.springboot.greencommute.dtoTest;

import com.springboot.greencommute.dto.UserDto;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDtoTest {

    UserDto userDto = new UserDto(1,"TestDTO");

    @Test
    void dtoEntityTest(){
        Assertions.assertEquals(1,userDto.getUserId());
        Assertions.assertEquals("TestDTO",userDto.getUserName());
        userDto = new UserDto();
        userDto.setUserId(2);
        userDto.setUserName("testDto2");

        Assertions.assertEquals(2,userDto.getUserId());
        Assertions.assertEquals("testDto2",userDto.getUserName());

        Assertions.assertEquals("UserDto(userId=2, userName=testDto2)",userDto.toString());
    }
}
