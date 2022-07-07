package com.example.BackWeb.reserva.infrastructure.controller.v0;

import com.example.BackWeb.reserva.application.ReservaService;
import com.example.BackWeb.reserva.infrastructure.controller.dto.ReservaDisponibleOutputDTO;
import com.example.BackWeb.reserva.infrastructure.controller.dto.ReservaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v0/reserva")
public class ReservaController {
    @Autowired
    ReservaService reservaService;
  /*
    @PostMapping
    public ResponseEntity<Object> addReserva(@RequestBody ReservaInputDTO reservaInputDTO, @RequestHeader String authorize) {
        String emailToken = AuthUtils.getEmail(authorize);
        if(!emailToken.equals(reservaInputDTO.getEmail())) {
            throw new UnprocesableException("La reserva no se pudo autorizar");
        }

        return reservaService.addReserva(reservaInputDTO);
    }
   */


    @GetMapping("detalle/{ciudadDestino}")
    public ResponseEntity<List<ReservaOutputDTO>> reservaFiltrada(@PathVariable String ciudadDestino , @RequestParam("fechaInferior")  String fechaInferior , @RequestParam("horaInferior") float horaInferior) {
        List<ReservaOutputDTO> listreserva = reservaService.filtroReservas(ciudadDestino,fechaInferior,horaInferior);
        return new ResponseEntity<>(listreserva, HttpStatus.OK);
    }
    @GetMapping("detalledisponible/{ciudadDestino}")
    public ResponseEntity<List<ReservaDisponibleOutputDTO>> disponibleFiltrada(@PathVariable String ciudadDestino ,  @RequestParam("fechaInferior")  String fechaInferior ,  @RequestParam("horaInferior") float horaInferior) {
        List<ReservaDisponibleOutputDTO> listreserva = reservaService.PlazasLibre(ciudadDestino,fechaInferior,horaInferior);
        return new ResponseEntity<>(listreserva, HttpStatus.OK);
    }

}
