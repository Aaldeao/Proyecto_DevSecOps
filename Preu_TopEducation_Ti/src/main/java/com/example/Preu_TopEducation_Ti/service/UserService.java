package com.example.Preu_TopEducation_Ti.service;

import com.example.Preu_TopEducation_Ti.entities.UserEntity;
import com.example.Preu_TopEducation_Ti.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity CreateUser(UserEntity user) {
        return userRepository.save(user);
    }

    public int UserCheck(String username, String password) {
        ArrayList<UserEntity> user = userRepository.findByUsername(username);
        if (user.size() == 0) {
            return 0;
        } else {
            if (user.get(0).getPassword().equals(password)) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
