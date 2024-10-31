package com.example.Preu_TopEducation_Ti.controllers;

import com.example.Preu_TopEducation_Ti.entities.UserEntity;
import com.example.Preu_TopEducation_Ti.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
@Controller
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") UserEntity user) {
        System.out.println(user.getUsername());
        if (userService.UserCheck(user.getUsername(), user.getPassword()) == 1) {
            return "index";
        } else {
            return "login";
        }
    }
}
