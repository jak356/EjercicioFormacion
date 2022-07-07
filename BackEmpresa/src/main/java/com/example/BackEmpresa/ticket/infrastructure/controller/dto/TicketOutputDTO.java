package com.example.BackEmpresa.ticket.infrastructure.controller.dto;

import com.example.BackEmpresa.ticket.domain.TicketEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TicketOutputDTO implements Serializable {
    private UUID idTicket;

    private String details;

    private UUID idUser;

    private UUID idReserva;

    public TicketOutputDTO (TicketEntity ticket){
        setIdTicket(ticket.getIdTicket());
        setDetails(ticket.getDetails());
        setIdUser(ticket.getUser().getIdUser());
        setIdReserva(ticket.getReserva().getIdReserva());
    }
}
