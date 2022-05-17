package com.springboot.GreenCommute.controllers;

import com.springboot.GreenCommute.dto.UserDto;
import com.springboot.GreenCommute.entities.User;
import com.springboot.GreenCommute.exception.DataNotFoundException;
import com.springboot.GreenCommute.mapper.UserMapper;
import com.springboot.GreenCommute.service.SavedJobService;
import com.springboot.GreenCommute.service.UserService;
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

    @GetMapping("/users")
    public List<UserDto> getListOfUsers(){
        List<User> userList =  userService.getUserList();
        return userMapper.toUserDtoList(userList);
    }

    @GetMapping("/users/{userId}")
    public UserDto getUserById(@PathVariable int userId){
        Optional<User> user = userService.getUserById(userId);
        if(user.isEmpty())
            throw new DataNotFoundException("No user found with id: " + userId);
        return userMapper.toUserDto(user.get());
    }

    @PostMapping("/users")
    public String addUser(@RequestBody User user){
        userService.addUser(user);
        return ("Successfully added the user");
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUserById(@PathVariable int userId){
        userService.deleteUserById(userId);
        return("Deleted successfully user with id: " + userId);
    }



}

