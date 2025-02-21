package com.example.KafkaRegisterService.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserEventListener {

    @KafkaListener(topics = "user-registration", groupId = "user-consumer-group")
    public void consumeUserEvent(ConsumerRecord<String, String> record) throws Exception  {

        System.out.println("📥 Evento recibido en Kafka: " + record.value());
    }




}
