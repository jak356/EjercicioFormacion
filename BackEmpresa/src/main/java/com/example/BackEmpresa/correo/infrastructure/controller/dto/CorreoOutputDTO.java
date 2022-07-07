package com.example.BackEmpresa.correo.infrastructure.controller.dto;

import com.example.BackEmpresa.correo.domain.CorreoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CorreoOutputDTO implements Serializable {
    private UUID idCorreo;
    private String ciudadDestino;
    private String email;
    private Date fechaReserva;
    private float horaReserva;

    public CorreoOutputDTO(CorreoEntity correo) {
        setIdCorreo(correo.getIdCorreo());
        setCiudadDestino(correo.getCiudadDestino());
        setEmail(correo.getEmail());
        setFechaReserva(correo.getFechaReserva());
        setHoraReserva(correo.getHoraReserva());
    }
    public String getDia()
    {
        return fechaReserva.toString().substring(0,2);
    }
}
