package com.example.BackEmpresa.reserva.infrastructure.controller.dto;

import com.example.BackEmpresa.reserva.domain.ReservaEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ReservaDisponibleOutputDTO implements Serializable {

    private UUID idReserva;
    private String ciudadDestino;
    private Date fechaSalida;
    private float horaSalida;
    private Integer numeroPlazas;

    public ReservaDisponibleOutputDTO(ReservaEntity reserva) {
        setIdReserva(reserva.getIdReserva());
        setCiudadDestino(reserva.getCiudadDestino());
        setFechaSalida(reserva.getFechaSalida());
        setHoraSalida(reserva.getHoraSalida());
        setNumeroPlazas(reserva.getNumeroPlazas());
    }


}
