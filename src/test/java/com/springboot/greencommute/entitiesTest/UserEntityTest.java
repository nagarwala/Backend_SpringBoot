package com.springboot.greencommute.entitiesTest;

import com.springboot.greencommute.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserEntityTest {

    @Test
    public void userTest(){

        User user = new User(1,"testUser",null);
        Assertions.assertEquals(1,user.getUserId());
        Assertions.assertEquals("testUser",user.getUserName());
        Assertions.assertNull(user.getSavedJobList());

        User newUser = new User();
        newUser.setUserId(2);
        newUser.setUserName("test2");
        newUser.setSavedJobList(null);

        Assertions.assertEquals(2,newUser.getUserId());
        Assertions.assertEquals("test2",newUser.getUserName());
        Assertions.assertNull(newUser.getSavedJobList());
    }
}
