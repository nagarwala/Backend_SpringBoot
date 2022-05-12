package com.springBoot.GreenCommute.service;

import com.springBoot.GreenCommute.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> getUserList();

    public Optional<User> getUserById(int userId);

    public void addUser(User user);

    public void deleteUserById(int userId);
}

