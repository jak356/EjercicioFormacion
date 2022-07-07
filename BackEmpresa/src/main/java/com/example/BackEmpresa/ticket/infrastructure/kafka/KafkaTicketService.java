package com.example.BackEmpresa.ticket.infrastructure.kafka;

import com.example.BackEmpresa.reserva.domain.ReservaEntity;
import com.example.BackEmpresa.reserva.infrastructure.repository.ReservaRepository;
import com.example.BackEmpresa.ticket.application.TicketServiceImpl;
import com.example.BackEmpresa.ticket.domain.TicketEntity;
import com.example.BackEmpresa.ticket.infrastructure.controller.dto.TicketOutputDTO;
import com.example.BackEmpresa.ticket.infrastructure.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaTicketService {
    @Autowired
    TicketServiceImpl ticketService;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ReservaRepository reservaRepository;

    public void listenTopic(String action,TicketOutputDTO ticketOutputDTO) {
        switch(action) {
            case "create" -> {
                TicketEntity ticket = ticketService.ticketOutDtoToEntity(ticketOutputDTO);
                ReservaEntity reserva = reservaRepository.findById(ticketOutputDTO.getIdReserva()).stream().findFirst().orElseThrow();
                reserva.setIncrementarPlazas(1);
                reservaRepository.save(reserva);

                log.info("CREADO CON EXITO");
            }
            case "update" -> {
                log.info("NO PUEDES ACTUALIZAR EL TICKET!!");

            }
            case "delete" -> {
                reservaRepository.delete(reservaRepository.findById(ticketOutputDTO.getIdTicket()).orElseThrow());
                ReservaEntity reserva = reservaRepository.findById(ticketOutputDTO.getIdTicket()).stream().findFirst().orElseThrow();
                reserva.setDisminuirPlazas(1);
                reservaRepository.save(reserva);

                log.info("ELIMINACIÓN CON EXITO");
            }

            default -> {
                log.info("ERROR KAFKA SERVICE TICKET ! ACCION NO ESPECIFICADA");
            }

        }
    }
}
