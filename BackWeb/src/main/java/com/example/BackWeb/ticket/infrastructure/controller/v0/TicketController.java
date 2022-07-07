package com.example.BackWeb.ticket.infrastructure.controller.v0;

import com.example.BackWeb.ticket.application.TicketService;
import com.example.BackWeb.ticket.infrastructure.controller.dto.TicketInputDTO;
import com.example.BackWeb.ticket.infrastructure.controller.dto.TicketOutputDTO;
import com.example.BackWeb.ticket.infrastructure.repository.TicketRepository;
import com.example.BackWeb.utilidades.authutils.AuthUtils;
import com.example.BackWeb.utilidades.exceptions.UnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v0/ticket")
@RestController
public class TicketController {
    @Autowired
    TicketService ticketService;
    @Autowired
    TicketRepository ticketRepository;

    @Value("${urlempresa}")
    String EMPRESA;

    @PostMapping
    public TicketOutputDTO addTicket(@RequestBody TicketInputDTO ticketInputDTO, @RequestHeader("Authorization") String auth) {
        HttpEntity<Object> request = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<Void>re = new RestTemplate().exchange(EMPRESA + "/" + auth, HttpMethod.GET, request, Void.class);

        if(re.getStatusCode() == HttpStatus.OK) {
            UUID idToken = AuthUtils.getId(auth);
            if(!idToken.equals(ticketInputDTO.getIdUser())){
                throw new UnprocesableException("EL id del ticket no coincide con el id de la persona autenticado");
            }
            return ticketService.addTicket(ticketInputDTO);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Autenticacion incorrecta");

    }

    @DeleteMapping("{íd}")
    public void deleteTicket(@PathVariable UUID id, @RequestHeader("Authorization") String auth) {
        HttpEntity<Object> request = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<Void> re = new RestTemplate().exchange("EMPRESA" + "/" + auth,HttpMethod.GET,request, Void.class);


        if(re.getStatusCode() == HttpStatus.OK) {

            UUID idToken = AuthUtils.getId(auth);
            if (!idToken.equals(ticketRepository.findById(id).orElseThrow().getUser().getIdUser())) {
                throw new UnprocesableException("EL id del ticket no coincide con el id de la persona autenticado");
            }
            ticketService.deleteTicket(id);
            return;
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Autenticacion incorrecta");
    }

    @GetMapping
    public List<TicketOutputDTO> findAll() {
        return ticketService.getAllTickets();
    }

    @GetMapping("{id}")
    public TicketOutputDTO filterById(@PathVariable UUID id, @RequestHeader("Authorization") String auth) {

        HttpEntity<Object> request = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<Void> re = new RestTemplate().exchange("EMPRESA" + "/" + auth,HttpMethod.GET,request, Void.class);

        if(re.getStatusCode() == HttpStatus.OK) {
            UUID idToken = AuthUtils.getId(auth);
            if (!idToken.equals(ticketRepository.findById(id).orElseThrow().getUser().getIdUser())) {
                throw new UnprocesableException("EL id del ticket no coincide con el id de la persona autenticado");
            }
            return ticketService.getTicketById(id);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Autenticacion incorrecta");
    }
}
