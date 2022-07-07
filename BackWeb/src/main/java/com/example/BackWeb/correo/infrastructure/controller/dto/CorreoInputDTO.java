package com.example.BackWeb.correo.infrastructure.controller.dto;

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
    private Date fechaReserva;
    private float horaReserva;

}
