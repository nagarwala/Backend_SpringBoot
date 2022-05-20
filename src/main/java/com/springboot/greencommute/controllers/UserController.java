package com.springboot.greencommute.controllers;

import com.springboot.greencommute.dto.UserDto;
import com.springboot.greencommute.entities.User;
import com.springboot.greencommute.exception.DataNotFoundException;
import com.springboot.greencommute.mapper.UserMapper;
import com.springboot.greencommute.service.SavedJobService;
import com.springboot.greencommute.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SavedJobService savedJobService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/users/{userId}")
    public UserDto getUserById(@PathVariable int userId){
        Optional<User> user = userService.getUserById(userId);
        if(user.isEmpty())
            throw new DataNotFoundException("No user found with id: " + userId);
        return userMapper.toUserDto(user.get());
    }
}

