package com.example.BackWeb.ticket.domain;


import com.example.BackWeb.reserva.domain.ReservaEntity;
import com.example.BackWeb.reserva.infrastructure.repository.ReservaRepository;
import com.example.BackWeb.ticket.infrastructure.controller.dto.TicketInputDTO;
import com.example.BackWeb.ticket.infrastructure.controller.dto.TicketOutputDTO;
import com.example.BackWeb.user.domain.UserEntity;
import com.example.BackWeb.user.infrastructure.repository.UserRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class TicketEntity {
    @Id @Column(name = "id_ticket")
    private UUID idTicket;

    private String details;

    @ManyToOne @JoinColumn(name= "fk_user") @EqualsAndHashCode.Exclude @ToString.Exclude
    private UserEntity user;

    @ManyToOne @JoinColumn(name= "fk_reserva") @EqualsAndHashCode.Exclude @ToString.Exclude
    private ReservaEntity reserva;




}
