package com.springboot.greencommute.service;

import com.springboot.greencommute.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public Optional<User> getUserById(int userId);

}

