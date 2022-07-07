package com.example.BackEmpresa.correo.application;

import com.example.BackEmpresa.correo.domain.CorreoEntity;
import com.example.BackEmpresa.correo.infrastructure.controller.dto.CorreoInputDTO;
import com.example.BackEmpresa.correo.infrastructure.controller.dto.CorreoOutputDTO;
import com.example.BackEmpresa.reserva.infrastructure.controller.dto.ReservaOutputDTO;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.UUID;

public interface CorreoService {
    ResponseEntity<List<CorreoEntity>> findAll();

    CorreoEntity filterCorreoById(UUID id);

    CorreoOutputDTO addCorreo(CorreoInputDTO correoInputDTO);

    ResponseEntity<ReservaOutputDTO> reenvioCorreo(CorreoInputDTO correoInputDto);

    ResponseEntity<List<CorreoOutputDTO>> filtroCorreo(String ciudadDestino, String fechaReserva, float horaReserva);

}
