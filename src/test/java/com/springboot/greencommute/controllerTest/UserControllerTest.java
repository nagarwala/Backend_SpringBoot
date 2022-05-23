package com.springboot.greencommute.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.greencommute.controllers.UserController;
import com.springboot.greencommute.dto.UserDto;
import com.springboot.greencommute.entities.User;
import com.springboot.greencommute.mapper.UserMapper;
import com.springboot.greencommute.service.UserService;
import com.springboot.greencommute.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    UserServiceImpl userService;

    @InjectMocks
    UserController userController;

    @Mock
    UserMapper userMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getUserByIdTest() throws Exception {
        Optional<User> userOptional = Optional.of(new User(1,"test1",null));
        UserDto userDto = userMapper.toUserDto(userOptional.get());
        Mockito.when(userService.getUserById(1)).thenReturn(userOptional);
        mockMvc.perform(MockMvcRequestBuilders.get("/users/1").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(userDto))).
                andDo(MockMvcResultHandlers.print());
        verify(userService).getUserById(1);
        verify(userService, times(1)).getUserById(1);
    }
}
