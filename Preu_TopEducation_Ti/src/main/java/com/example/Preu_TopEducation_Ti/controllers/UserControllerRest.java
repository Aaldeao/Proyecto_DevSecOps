package com.example.Preu_TopEducation_Ti.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.Preu_TopEducation_Ti.entities.UserEntity;
import com.example.Preu_TopEducation_Ti.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")


public class UserControllerRest {
    @Autowired
    private UserService userService;

    // Endpoint para crear un usuario
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserEntity user) {
        try {
            userService.CreateUser(user); // Guarda el usuario
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
