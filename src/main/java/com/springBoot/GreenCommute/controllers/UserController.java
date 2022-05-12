package com.springBoot.GreenCommute.controllers;

import com.springBoot.GreenCommute.dto.UserDto;
import com.springBoot.GreenCommute.entities.User;
import com.springBoot.GreenCommute.exception.DataNotFoundException;
import com.springBoot.GreenCommute.mapper.UserMapper;
import com.springBoot.GreenCommute.service.SavedJobService;
import com.springBoot.GreenCommute.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
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
        List<UserDto> userDtoList = userMapper.toUserDtoList(userList);
        return userDtoList;
    }

    @GetMapping("/users/{userId}")
    public UserDto getUserById(@PathVariable int userId){
        Optional<User> user = userService.getUserById(userId);
        if(user.isEmpty())
            throw new DataNotFoundException("No user found with id: " + userId);
        UserDto userDto = userMapper.toUserDto(user.get());
        return userDto;
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

