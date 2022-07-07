package com.example.BackWeb.utilidades.kafka;


import com.example.BackWeb.correo.infrastructure.controller.dto.CorreoOutputDTO;
import com.example.BackWeb.correo.infrastructure.kafka.KafkaCorreoService;
import com.example.BackWeb.reserva.domain.ReservaEntity;
import com.example.BackWeb.reserva.infrastructure.controller.dto.ReservaOutputDTO;
import com.example.BackWeb.reserva.infrastructure.kafka.KafkaReservaService;
import com.example.BackWeb.ticket.infrastructure.controller.dto.TicketOutputDTO;
import com.example.BackWeb.ticket.infrastructure.kafka.KafkaTicketService;
import com.example.BackWeb.user.infrastructure.dto.output.UserOutputDTO;
import com.example.BackWeb.user.infrastructure.kafka.KafkaUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaRouter {
    @Value("${server.port}")
    String puerto;
    @Autowired
    KafkaUserService kafkaUserService;
    @Autowired
    KafkaReservaService kafkaReservaService;
    @Autowired
    KafkaCorreoService kafkaCorreoService;
    @Autowired
    KafkaTicketService kafkaTicketService;


    @KafkaListener(topics = "${topic}", groupId = "${group}")
    public void listenTopic(@Payload ConsumerRecord<String, Object> record) throws JsonProcessingException {
        final String[] port = new String[1];
        final String[] action = new String[1];
        final String[] clase = new String[1];
        ObjectMapper mapper = new ObjectMapper();

        record.headers().headers("port").forEach(header -> {
            port[0] = new String(header.value());
        });
        record.headers().headers("action").forEach(header -> {
            action[0] = new String(header.value());
        });
        record.headers().headers("clase").forEach(header -> {
            clase[0] = new String(header.value());
        });

        if (!port[0].equals(puerto)) {
            switch (clase[0]) {
                case "reserva" -> {
                    System.out.println("Recibido reserva accion: " + action[0]);
                    kafkaReservaService.listenTopic(action[0], mapper.readValue((String)record.value(), ReservaOutputDTO.class));

                }
                case "ticket" -> {
                    System.out.println("Recibido ticket accion: " + action[0]);
                    kafkaTicketService.listenTopic(action[0],mapper.readValue((String)record.value(), TicketOutputDTO.class));
                }

                case "correo" -> {
                    System.out.println("Recibido correo accion: " + action[0]);
                    kafkaCorreoService.listenTopic(action[0],mapper.readValue((String)record.value(), CorreoOutputDTO.class));
                }
                case "user" -> {
                    System.out.println("Recibido usuario accion: " + action[0]);
                    kafkaUserService.listenTopic(action[0], mapper.readValue((String)record.value(), UserOutputDTO.class));
                }
                default -> System.out.println("error");
            }
        }

    }
}
