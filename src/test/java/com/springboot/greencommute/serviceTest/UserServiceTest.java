package com.springboot.greencommute.serviceTest;

import com.springboot.greencommute.entities.User;
import com.springboot.greencommute.repositories.UserRepository;
import com.springboot.greencommute.service.UserService;
import com.springboot.greencommute.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
 class UserServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void initUseCase(){
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void getUserByIdTest() {
        Optional<User> user = Optional.of(new User(1, "Nitesh", null));
        Mockito.when(userRepository.findById(1)).thenReturn(user);
        Assertions.assertEquals(user,userService.getUserById(1));
        Mockito.verify(userRepository).findById(1);
    }


}

