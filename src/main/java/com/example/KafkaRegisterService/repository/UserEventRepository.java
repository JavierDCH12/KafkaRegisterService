package com.example.KafkaRegisterService.repository;

import com.example.KafkaRegisterService.model.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEventRepository extends JpaRepository<UserEvent, Long> {

    List<UserEvent> findByEmail(String email);





}
