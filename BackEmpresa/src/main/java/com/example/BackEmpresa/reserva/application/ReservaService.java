package com.example.BackEmpresa.reserva.application;

import com.example.BackEmpresa.reserva.infrastructure.controller.dto.ReservaDisponibleOutputDTO;
import com.example.BackEmpresa.reserva.infrastructure.controller.dto.ReservaInputDTO;
import com.example.BackEmpresa.reserva.infrastructure.controller.dto.ReservaOutputDTO;

import java.util.List;
import java.util.UUID;

public interface ReservaService {
    List<ReservaOutputDTO> getAllReserva();

    ReservaOutputDTO filterReservaById(UUID id);

    ReservaOutputDTO updateReserva(UUID id, ReservaInputDTO reservaInputDTO);

    void deleteReserva(UUID id);

   List<ReservaOutputDTO> filtroReservas(String ciudadDestino, String fechaSalida, float horaSalida);

    ReservaOutputDTO addReserva(ReservaInputDTO reservaInputDTO);

    List<ReservaDisponibleOutputDTO> PlazasLibre(String ciudadDestino,String fechaSalida, float horaSalida);

}
