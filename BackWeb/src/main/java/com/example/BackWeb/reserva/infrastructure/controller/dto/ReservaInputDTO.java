package com.example.BackWeb.reserva.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaInputDTO implements Serializable {
  @NonNull
  private String ciudadDestino;

  @NonNull
  private String nombre;

  @NonNull

  private String telefono;

  private String email;

  @NonNull
  private Date fechaSalida;

  @NonNull
  private long horaSalida;
}
