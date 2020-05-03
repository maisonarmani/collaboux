package com.randomsturvs.collaboux.services;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

@Service
@DependsOn("kafkaProducer")
public class NotificationService  {

    @Autowired
    KafkaProducer<String,String> kafkaProducer;

}
