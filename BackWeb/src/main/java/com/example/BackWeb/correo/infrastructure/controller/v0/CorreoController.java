package com.example.BackWeb.correo.infrastructure.controller.v0;

import com.example.BackWeb.correo.application.CorreoService;
import com.example.BackWeb.correo.domain.CorreoEntity;
import com.example.BackWeb.correo.infrastructure.controller.dto.CorreoInputDTO;
import com.example.BackWeb.correo.infrastructure.controller.dto.CorreoOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v0/correo")
public class CorreoController {
    @Autowired
    CorreoService correoService;

/*
    @PostMapping
    public CorreoOutputDTO addCorreo(@RequestBody CorreoInputDTO correoInputDTO) {
        return correoService.addCorreo(correoInputDTO);
    }

    @GetMapping("todos")
    public List<CorreoEntity> findAll() {
        return correoService.findAll();
    }

    @GetMapping("{id}")
    public CorreoEntity filterCorreoById(@PathVariable UUID id) {
        return correoService.filterCorreoById(id);
    }
 */

}
