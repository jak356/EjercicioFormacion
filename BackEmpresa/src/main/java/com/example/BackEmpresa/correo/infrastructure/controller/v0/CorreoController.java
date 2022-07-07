package com.example.BackEmpresa.correo.infrastructure.controller.v0;

import com.example.BackEmpresa.correo.application.CorreoService;
import com.example.BackEmpresa.correo.domain.CorreoEntity;
import com.example.BackEmpresa.correo.infrastructure.controller.dto.CorreoInputDTO;
import com.example.BackEmpresa.correo.infrastructure.controller.dto.CorreoOutputDTO;
import com.example.BackEmpresa.reserva.infrastructure.controller.dto.ReservaOutputDTO;
import com.example.BackEmpresa.utilidades.authutils.AuthUtils;
import com.example.BackEmpresa.utilidades.exceptions.UnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v0-empresa/correo")
public class CorreoController {
    @Autowired
    CorreoService correoService;

    @PostMapping
    public CorreoOutputDTO addCorreo(@RequestBody CorreoInputDTO correoInputDTO) {
        return correoService.addCorreo(correoInputDTO);
    }
    @GetMapping
    public ResponseEntity<List<CorreoOutputDTO>> filtroCorreo(@PathVariable String ciudadDestino, @RequestParam("fechaReserva") String fechaReserva, @RequestParam("horaReserva") float horaReserva)
    {
        return correoService.filtroCorreo(ciudadDestino,fechaReserva,horaReserva);

    }

    @GetMapping("getall")
    public ResponseEntity<List<CorreoEntity>> findAll() {
        return correoService.findAll();
    }

    @GetMapping("{id}")
    public CorreoEntity filterCorreoById(@PathVariable UUID id) {
        return correoService.filterCorreoById(id);
    }

    @PutMapping
    public ResponseEntity<ReservaOutputDTO> reenviarCorreo(@RequestBody CorreoInputDTO correoInputDTO ,@RequestHeader("Authorization") String authorize)
    {
        String emailToken = AuthUtils.getEmail(authorize);
        if(!emailToken.equals(correoInputDTO.getEmail())) {
            throw new UnprocesableException("el reenvio no se pudo autorizar");
        }
       return correoService.reenvioCorreo(correoInputDTO);

    }


}
