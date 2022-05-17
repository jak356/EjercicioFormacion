package com.example.EJKA1.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics ="${message.topic.name:test}", groupId = "${message.group.name:testgroup}")
    public void listenTopic(String message)
    {
        System.out.println("Recibe el mensaje del topic:" + message);
    }
}
