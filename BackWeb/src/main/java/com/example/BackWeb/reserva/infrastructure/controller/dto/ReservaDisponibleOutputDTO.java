package com.example.BackWeb.reserva.infrastructure.controller.dto;

import com.example.BackWeb.reserva.domain.ReservaEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
public class ReservaDisponibleOutputDTO implements Serializable {

    private String ciudadDestino;
    private Date fechaSalida;
    private float horaSalida;
    private Integer numeroPlazas;

    public ReservaDisponibleOutputDTO(ReservaEntity reserva) {
        setCiudadDestino(reserva.getCiudadDestino());
        setFechaSalida(reserva.getFechaSalida());
        setHoraSalida(reserva.getHoraSalida());
        setNumeroPlazas(reserva.getNumeroPlazas());
    }



}
