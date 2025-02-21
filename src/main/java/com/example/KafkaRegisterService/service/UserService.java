package com.example.KafkaRegisterService.service;

import com.example.KafkaRegisterService.model.User;
import com.example.KafkaRegisterService.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    //Add logger
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    private  final UserRepository userRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public UserService(UserRepository userRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.userRepository = userRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User registerUser(String email, String name){
        if(userRepository.findByEmail(email).isPresent()){
            logger.info("FAILED: User with email {} already exists", email);
            throw  new RuntimeException("User with this email already exists");
        }

        //Save user
        var user = new User(email, name);
        userRepository.save(user);

        String eventMessage = "SUCCESS: User {} registered successfully"+ email;
        logger.info("SUCCESS: User {} registered successfully", email);

        kafkaTemplate.send("user-registration", eventMessage);


        return user;

    }


}

