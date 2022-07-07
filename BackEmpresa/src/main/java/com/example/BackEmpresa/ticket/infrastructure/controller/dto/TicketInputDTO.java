package com.example.BackEmpresa.ticket.infrastructure.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TicketInputDTO implements Serializable {

    private String details;


    private UUID idUser;


    private UUID idReserva;
}
