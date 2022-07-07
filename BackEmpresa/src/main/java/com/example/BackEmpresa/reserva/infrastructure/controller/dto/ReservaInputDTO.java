package com.example.BackEmpresa.reserva.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaInputDTO implements Serializable {

  private String ciudadDestino;

  private String nombre;

  private String telefono;

  private String email;

  private Date fechaSalida;

  private long horaSalida;
}
