package com.example.BackWeb.reserva.infrastructure.kafka;


import com.example.BackWeb.reserva.application.ReservaService;
import com.example.BackWeb.reserva.application.ReservaServiceImpl;
import com.example.BackWeb.reserva.domain.ReservaEntity;
import com.example.BackWeb.reserva.infrastructure.controller.dto.ReservaDisponibleOutputDTO;
import com.example.BackWeb.reserva.infrastructure.controller.dto.ReservaOutputDTO;
import com.example.BackWeb.reserva.infrastructure.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaReservaService {
    @Autowired
    ReservaServiceImpl reservaService;

    @Autowired
    ReservaRepository reservaRepository;

    public void listenTopic(String action, ReservaOutputDTO reservaOutputDTO) {
        switch (action) {
            case "crear" -> {
                ReservaEntity reserva = reservaService.reservaOutputDtoToEntity(reservaOutputDTO);
                reservaRepository.save(reserva);
                System.out.println("exito en la craeción");

            }
            case "eliminar" -> {
                reservaRepository.delete(reservaRepository.findById(reservaOutputDTO.getIdReserva()).orElseThrow());
                System.out.println("USUARIO ELIMINADO");
            }
            case "update" -> {
                ReservaEntity reserva = reservaRepository.findById(reservaOutputDTO.getIdReserva()).orElseThrow();

                reserva.setCiudadDestino(reservaOutputDTO.getCiudadDestino());
                reserva.setNombre(reservaOutputDTO.getNombre());
                reserva.setTelefono(reservaOutputDTO.getTelefono());
                reserva.setEmail(reservaOutputDTO.getEmail());
                reserva.setFechaSalida(reservaOutputDTO.getFechaReserva());
                reserva.setHoraSalida(reservaOutputDTO.getHoraReserva());

                reservaRepository.save(reserva);

                System.out.println("USUARIO ACTUALIZADO");

            }
            default -> {
                System.out.println("ERROR KAFKA SERVICE RESERVA ! ACCION NO ESPECIFICADA()");
            }
        }
    }
}
