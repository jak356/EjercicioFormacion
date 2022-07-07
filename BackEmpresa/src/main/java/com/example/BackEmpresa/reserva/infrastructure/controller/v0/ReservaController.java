package com.example.BackEmpresa.reserva.infrastructure.controller.v0;

import com.example.BackEmpresa.reserva.application.ReservaService;
import com.example.BackEmpresa.reserva.infrastructure.controller.dto.ReservaDisponibleOutputDTO;
import com.example.BackEmpresa.reserva.infrastructure.controller.dto.ReservaInputDTO;
import com.example.BackEmpresa.reserva.infrastructure.controller.dto.ReservaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v0-empresa/reserva")
public class ReservaController {
    @Autowired
    ReservaService reservaService;

    @PostMapping
    public ReservaOutputDTO addReserva(@RequestBody ReservaInputDTO reservaInputDTO) {
        return reservaService.addReserva(reservaInputDTO);
    }

    @GetMapping("detalle/{ciudadDestino}")
    public ResponseEntity<List<ReservaOutputDTO>> reservaFiltrada(@PathVariable String ciudadDestino , @RequestParam("fechaInferior")  String fechaInferior , @RequestParam("horaInferior") float horaInferior) {
        List<ReservaOutputDTO> listreserva = reservaService.filtroReservas(ciudadDestino,fechaInferior,horaInferior);
        return new ResponseEntity<>(listreserva, HttpStatus.OK);
    }

    @GetMapping("detalledisponible/{ciudadDestino}")
    public ResponseEntity<List<ReservaDisponibleOutputDTO>> disponibleFiltrada(@PathVariable String ciudadDestino , @RequestParam("fechaInferior") String fechaInferior ,  @RequestParam("horaInferior") float horaInferior) {
        List<ReservaDisponibleOutputDTO> listreserva = reservaService.PlazasLibre(ciudadDestino,fechaInferior,horaInferior);
        return new ResponseEntity<>(listreserva, HttpStatus.OK);
    }

    @GetMapping("getall")
    public List<ReservaOutputDTO> findAll() {
        return reservaService.getAllReserva();
    }

    @GetMapping("{id}")
    public ReservaOutputDTO findById(UUID id){
        return reservaService.filterReservaById(id);
    }

    @PutMapping("{id}")
    public ReservaOutputDTO updateReserva(@PathVariable UUID id, @RequestBody ReservaInputDTO reservaInputDTO)
    {
        return reservaService.updateReserva(id, reservaInputDTO);
    }

    @DeleteMapping("{id}")
    public void deleteReserva(@PathVariable UUID id) {
        reservaService.deleteReserva(id);
    }

}
