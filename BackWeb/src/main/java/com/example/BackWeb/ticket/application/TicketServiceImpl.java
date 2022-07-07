package com.example.BackWeb.ticket.application;


import com.example.BackWeb.correo.domain.CorreoEntity;
import com.example.BackWeb.correo.infrastructure.repository.CorreoRepository;
import com.example.BackWeb.reserva.domain.ReservaEntity;
import com.example.BackWeb.reserva.infrastructure.repository.ReservaRepository;
import com.example.BackWeb.ticket.domain.TicketEntity;
import com.example.BackWeb.ticket.infrastructure.controller.dto.TicketInputDTO;
import com.example.BackWeb.ticket.infrastructure.controller.dto.TicketOutputDTO;
import com.example.BackWeb.ticket.infrastructure.repository.TicketRepository;
import com.example.BackWeb.user.domain.UserEntity;
import com.example.BackWeb.user.infrastructure.repository.UserRepository;
import com.example.BackWeb.utilidades.correoenviar.CorreoSenderService;
import com.example.BackWeb.utilidades.exceptions.UnprocesableException;
import com.example.BackWeb.utilidades.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TicketServiceImpl implements TicketService{
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReservaRepository reservaRepository;
    @Autowired
    CorreoRepository correoRepository;
    @Autowired
    KafkaProducer sender;
    @Autowired
    CorreoSenderService correoSenderService;
    @Value("${server.port}")
    String port;
    @Value("${topic}")
    String topic;

    String TICKET = "ticket";

    String CREATE = "create";



    @Override
    public List<TicketOutputDTO> getAllTickets() {
        List<TicketEntity> tickets = ticketRepository.findAll();
        return tickets.stream().map(TicketOutputDTO::new).toList();
    }

    @Override
    public TicketOutputDTO getTicketById(UUID id) {
        TicketEntity ticket = ticketRepository.findById(id).orElseThrow();
        return new TicketOutputDTO(ticket);
    }

    @Override
    public TicketOutputDTO addTicket(TicketInputDTO ticketInputDTO) {
        UserEntity user = userRepository.findById(ticketInputDTO.getIdUser()).orElseThrow();
        ReservaEntity reserva = reservaRepository.findById(ticketInputDTO.getIdReserva()).stream().findFirst().orElseThrow();

        TicketEntity ticket = ticketInputDtoToEntity(ticketInputDTO);
        ticket.setIdTicket(UUID.randomUUID());

        if(reserva.getNumeroPlazas() >=1) {
            reserva.setDisminuirPlazas(1);
            ticketRepository.save(ticket);

            TicketOutputDTO ticketOutputDTO = new TicketOutputDTO(ticket);
            sender.sendMessage(topic, ticketOutputDTO, port,CREATE, TICKET);

            correoSenderService.sendMail(
                    ticket.getUser().getEmail(),
                    "Ticket comprado",
                    "Se ha adquirido un ticket para el viaje reservado en "
                            + reserva.getFechaReserva() + "con salida " + reserva.getHoraReserva() +
                            "con identificador " + ticket.getIdTicket());
            CorreoEntity correo =new CorreoEntity(UUID.randomUUID(), reserva.getCiudadDestino(),reserva.getEmail(),reserva.getFechaReserva(),reserva.getHoraReserva());
            correoRepository.save(correo);
            sender.sendMessage(topic,correo,port,CREATE,"correo");
            return ticketOutputDTO;
        }
        throw new UnprocesableException("No quedan plazas para este bus");
    }

    @Override
    public void deleteTicket(UUID id) {
        TicketEntity ticket = ticketRepository.findById(id).orElseThrow();
        ReservaEntity reserva = reservaRepository.findById(ticketRepository.findById(id).orElseThrow().getReserva().getIdReserva()).orElseThrow();
        ticketRepository.delete(ticketRepository.findById(id).orElseThrow());
        reserva.setIncrementarPlazas(1);
        reservaRepository.save(reserva);

        TicketOutputDTO ticketOutputDTO = new TicketOutputDTO(ticket);
        sender.sendMessage(topic,ticketOutputDTO,port,"delete",TICKET);

        correoSenderService.sendMail(
                ticket.getUser().getEmail(),
                "Ticket eliminado",
                "Su ticket has sido cancelado para el viaje reservado en"
                        +reserva.getFechaReserva() +"con salida" + reserva.getFechaSalida());

        CorreoEntity correo = new CorreoEntity(UUID.randomUUID(), reserva.getCiudadDestino(),reserva.getEmail(),reserva.getFechaReserva(),reserva.getHoraReserva());
        correoRepository.save(correo);
        sender.sendMessage(topic,correo,port,CREATE,"correo");

    }
    public TicketEntity ticketInputDtoToEntity(TicketInputDTO ticketInputDTO) {
        TicketEntity ticket = new TicketEntity();
        ticket.setDetails(ticketInputDTO.getDetails());
        ticket.setUser(userRepository.findById(ticketInputDTO.getIdUser()).orElseThrow());
        ticket.setReserva(reservaRepository.findById(ticketInputDTO.getIdReserva()).orElseThrow());

        return ticket;
    }

    public TicketEntity ticketOutDtoToEntity(TicketOutputDTO ticketOutputDTO){
        TicketEntity ticket = new TicketEntity();
        ticket.setIdTicket(ticketOutputDTO.getIdTicket());
        ticket.setDetails(ticketOutputDTO.getDetails());
        ticket.setUser(userRepository.findById(ticketOutputDTO.getIdUser()).orElseThrow());
        ticket.setReserva(reservaRepository.findById(ticketOutputDTO.getIdReserva()).orElseThrow());

        return ticket;
    }

    public TicketOutputDTO EntityToTicketOutDto(TicketEntity ticket) {
        TicketOutputDTO ticketOutputDto = new TicketOutputDTO();

        ticketOutputDto.setIdTicket(ticket.getIdTicket());
        ticketOutputDto.setDetails(ticket.getDetails());
        ticketOutputDto.setIdUser(ticket.getUser().getIdUser());
        ticketOutputDto.setIdReserva(ticket.getReserva().getIdReserva());

        return ticketOutputDto;
    }
}
