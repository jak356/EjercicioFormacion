package com.example.BackWeb.ticket.infrastructure.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TicketInputDTO implements Serializable {

    private String details;

    @NonNull
    private UUID idUser;

    @NonNull
    private UUID idReserva;
}
