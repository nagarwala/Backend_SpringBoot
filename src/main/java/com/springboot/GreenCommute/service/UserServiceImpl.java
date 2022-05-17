package com.springboot.GreenCommute.service;

import com.springboot.GreenCommute.entities.User;
import com.springboot.GreenCommute.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(int userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(int userId) {
        userRepository.deleteById(userId);
    }
}
