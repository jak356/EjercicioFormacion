package com.example.BackEmpresa.ticket.application;

import com.example.BackEmpresa.ticket.infrastructure.controller.dto.TicketInputDTO;
import com.example.BackEmpresa.ticket.infrastructure.controller.dto.TicketOutputDTO;

import java.util.List;
import java.util.UUID;

public interface TicketService {
    List<TicketOutputDTO> getAllTickets();
    TicketOutputDTO getTicketById(UUID id);
    TicketOutputDTO addTicket(TicketInputDTO ticketInputDTO);
    void deleteTicket (UUID id);

}
