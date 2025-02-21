package com.example.KafkaRegisterService.controller;

import com.example.KafkaRegisterService.model.User;
import com.example.KafkaRegisterService.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all)")
    public List<User> getAll() {
        return userService.getAllUsers();
    }




    @PostMapping("/register")
    public User registerUser(@RequestParam String name, @RequestParam String email) {
        return userService.registerUser(name, email);
    }
}
