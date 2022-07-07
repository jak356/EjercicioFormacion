package com.example.BackEmpresa.correo.infrastructure.controller.dto;

import com.example.BackEmpresa.correo.domain.CorreoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CorreoInputDTO implements Serializable {
    private String ciudadDestino;
    private String email;
    private Date fechaReserva = new Date();
    private float horaReserva = fechaReserva.getTime();
    public CorreoInputDTO(CorreoEntity correoEntity)
    {
        setCiudadDestino(correoEntity.getCiudadDestino());
        setEmail(correoEntity.getEmail());
        setFechaReserva(correoEntity.getFechaReserva());
        setHoraReserva(correoEntity.getHoraReserva());
    }

}
