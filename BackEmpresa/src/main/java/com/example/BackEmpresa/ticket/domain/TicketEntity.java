package com.example.BackEmpresa.ticket.domain;

import com.example.BackEmpresa.reserva.domain.ReservaEntity;
import com.example.BackEmpresa.user.domain.UserEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


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
