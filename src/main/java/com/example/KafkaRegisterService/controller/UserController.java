package com.example.KafkaRegisterService.controller;

import com.example.KafkaRegisterService.model.User;
import com.example.KafkaRegisterService.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }




    @PostMapping("/register")
    public User registerUser(@RequestParam String name, @RequestParam String email) {
        return userService.registerUser(name, email);
    }
}
