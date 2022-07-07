package com.example.BackEmpresa.utilidades.kafka;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;



@Service
@Slf4j
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


    public void sendMessage(String topic, Object obj, String port, String accion, String clase) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonObject = null;
        try {
            jsonObject = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException ignored) {
            log.info("Excepcion en procesar el json");
        }
        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(topic, jsonObject);
        producerRecord.headers().add("port", port.getBytes());
        producerRecord.headers().add("action", accion.getBytes());
        producerRecord.headers().add("clase", clase.getBytes());

        log.info("mensaje enviado");
        log.info("el mensaje es: " + producerRecord);
        kafkaTemplate.send(producerRecord);
    }
}
