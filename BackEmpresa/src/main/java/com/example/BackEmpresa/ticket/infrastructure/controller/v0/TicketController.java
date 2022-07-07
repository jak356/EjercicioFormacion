package com.example.BackEmpresa.ticket.infrastructure.controller.v0;

import com.example.BackEmpresa.ticket.application.TicketService;
import com.example.BackEmpresa.ticket.infrastructure.controller.dto.TicketInputDTO;
import com.example.BackEmpresa.ticket.infrastructure.controller.dto.TicketOutputDTO;
import com.example.BackEmpresa.ticket.infrastructure.repository.TicketRepository;
import com.example.BackEmpresa.utilidades.authutils.AuthUtils;
import com.example.BackEmpresa.utilidades.exceptions.UnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v0-empresa/ticket")
@RestController
public class TicketController {
    @Autowired
    TicketService ticketService;
    @Autowired
    TicketRepository ticketRepository;

    @PostMapping
    public TicketOutputDTO addTicket(@RequestBody TicketInputDTO ticketInputDTO, @RequestHeader("Authorization") String auth) {
        UUID idToken = AuthUtils.getId(auth);
        if(!idToken.equals(ticketInputDTO.getIdUser())) {
            throw new UnprocesableException("EL id del ticket no coincide con el id de la persona autenticado");
        }
        return ticketService.addTicket(ticketInputDTO);
    }
    @DeleteMapping("{íd}")
    public void deleteTicket(@PathVariable UUID id, @RequestHeader("Authorization") String authorize) {
        UUID idToken = AuthUtils.getId(authorize);
        if (!idToken.equals(ticketRepository.findById(id).orElseThrow().getUser().getIdUser())) {
            throw new UnprocesableException("EL id del ticket no coincide con el id de la persona autenticado");
        }
        ticketService.deleteTicket(id);
    }
    @GetMapping
    public List<TicketOutputDTO> findAll(){
        return ticketService.getAllTickets();
    }

    @GetMapping("{id}")
    public TicketOutputDTO filterById(@PathVariable UUID id, @RequestHeader("Authorization") String auth) {
        UUID idToken = AuthUtils.getId(auth);
        if(!idToken.equals(ticketRepository.findById(id).orElseThrow().getUser().getIdUser())) {
            throw new UnprocesableException("EL id del ticket no coincide con el id de la persona autenticado");
        }
        return ticketService.getTicketById(id);
    }


}
