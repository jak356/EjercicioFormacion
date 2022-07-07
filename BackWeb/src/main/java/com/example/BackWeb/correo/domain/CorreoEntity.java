package com.example.BackWeb.correo.domain;

import com.example.BackWeb.correo.infrastructure.controller.dto.CorreoInputDTO;
import com.example.BackWeb.correo.infrastructure.controller.dto.CorreoOutputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorreoEntity {
    @Id
    @Column(name = "id_correo")
    private UUID idCorreo;
    private String ciudadDestino;
    private String email;
    private Date fechaReserva;
    private float horaReserva;

    public CorreoEntity(CorreoOutputDTO correoOutputDTO) {
        setCiudadDestino(correoOutputDTO.getCiudadDestino());
        setEmail(correoOutputDTO.getEmail());
        setFechaReserva(correoOutputDTO.getFechaReserva());
        setHoraReserva(correoOutputDTO.getHoraReserva());

    }

    public CorreoEntity(CorreoInputDTO correoInputDTO) {
        setCiudadDestino(correoInputDTO.getCiudadDestino());
        setEmail(correoInputDTO.getEmail());
        setFechaReserva(correoInputDTO.getFechaReserva());
        setHoraReserva(correoInputDTO.getHoraReserva());
    }
}
