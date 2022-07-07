package com.example.BackEmpresa.reserva.domain;

import com.example.BackEmpresa.ticket.domain.TicketEntity;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class ReservaEntity {
    @Id
    @Column(name = "id_reserva")
    private UUID idReserva;
    private String ciudadDestino;
    private String nombre;
    private String telefono;
    private String email;
    private Date fechaReserva;
    private float horaReserva;
    private Date fechaSalida;
    private float horaSalida;
    private Integer numeroPlazas = 40;
    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<TicketEntity> tickets;

    public void setDisminuirPlazas(Integer plazas) {
        this.numeroPlazas -= plazas;
    }

    public void setIncrementarPlazas(Integer plazas){
        this.numeroPlazas += plazas;
    }


}
