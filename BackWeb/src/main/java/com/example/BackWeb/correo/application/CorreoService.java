package com.example.BackWeb.correo.application;


import com.example.BackWeb.correo.domain.CorreoEntity;
import com.example.BackWeb.correo.infrastructure.controller.dto.CorreoInputDTO;
import com.example.BackWeb.correo.infrastructure.controller.dto.CorreoOutputDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CorreoService {
    ResponseEntity<List<CorreoEntity>> findAll();

    CorreoEntity filterCorreoById(UUID id);

    CorreoOutputDTO addCorreo(CorreoInputDTO correoInputDTO);



}
