package com.example.BackWeb.ticket.infrastructure.kafka;


import com.example.BackWeb.reserva.domain.ReservaEntity;
import com.example.BackWeb.reserva.infrastructure.repository.ReservaRepository;
import com.example.BackWeb.ticket.application.TicketServiceImpl;
import com.example.BackWeb.ticket.domain.TicketEntity;
import com.example.BackWeb.ticket.infrastructure.controller.dto.TicketOutputDTO;
import com.example.BackWeb.ticket.infrastructure.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaTicketService {
    @Autowired
    TicketServiceImpl ticketService;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ReservaRepository reservaRepository;

    public void listenTopic(String action, TicketOutputDTO ticketOutputDTO) {
        switch(action) {
            case "create" -> {
                TicketEntity ticket = ticketService.ticketOutDtoToEntity(ticketOutputDTO) ;
                ReservaEntity reserva = reservaRepository.findById(ticketOutputDTO.getIdReserva()).stream().findFirst().orElseThrow();
                reserva.setIncrementarPlazas(1);
                reservaRepository.save(reserva);

                System.out.println("CREADO CON EXITO");
            }
            case "update" -> {
                System.out.println("NO PUEDES ACTUALIZAR EL TICKET!!");

            }
            case "delete" -> {
                reservaRepository.delete(reservaRepository.findById(ticketOutputDTO.getIdTicket()).orElseThrow());
                ReservaEntity reserva = reservaRepository.findById(ticketOutputDTO.getIdTicket()).stream().findFirst().orElseThrow();
                reserva.setDisminuirPlazas(1);
                reservaRepository.save(reserva);

                System.out.println("ELIMINACIÓN CON EXITO");
            }


        }
    }
}
