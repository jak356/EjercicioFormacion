package com.example.EJKA1.controller;

import com.example.EJKA1.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KaftkaController {
    @Autowired
    KafkaProducer kafkaProducer;

    @PostMapping("/add/{topic}")
    public void addTopic(@PathVariable String topic, @RequestBody String body)
    {
        kafkaProducer.sendMessage(topic,body);
    }
}
