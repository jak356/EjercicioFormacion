package com.example.BackWeb.reserva.application;


import com.example.BackWeb.reserva.infrastructure.controller.dto.ReservaDisponibleOutputDTO;
import com.example.BackWeb.reserva.infrastructure.controller.dto.ReservaInputDTO;
import com.example.BackWeb.reserva.infrastructure.controller.dto.ReservaOutputDTO;
import org.springframework.http.ResponseEntity;


import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ReservaService {
    List<ReservaOutputDTO> getAllReserva();

    ReservaOutputDTO filterReservaById(UUID id);

    ReservaOutputDTO updateReserva(UUID id, ReservaInputDTO reservaInputDTO);

    void deleteReserva(UUID id);

    List<ReservaOutputDTO> filtroReservas(String ciudadDestino, String fechaSalida, float horaSalida);

    ResponseEntity<Object> addReserva(ReservaInputDTO reservaInputDTO);

    List<ReservaDisponibleOutputDTO> PlazasLibre(String ciudadDestino, String fechaSalida, float horaSalida);


}
