package com.example.KafkaRegisterService.service;

import com.example.KafkaRegisterService.model.User;
import com.example.KafkaRegisterService.repository.UserRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    //Add logger


    private  final UserRepository userRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public UserService(UserRepository userRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.userRepository = userRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public User registerUser(String email, String name){
        if(userRepository.findByEmail(email).isPresent()){
            throw  new RuntimeException("User with this email already exists");
        }

        //Save user
        var user = new User(email, name);
        userRepository.save(user);

        kafkaTemplate.send("user-registration", "User registered: " + user.getEmail() + ", " + user.getName()  );


        return user;

    }


}

