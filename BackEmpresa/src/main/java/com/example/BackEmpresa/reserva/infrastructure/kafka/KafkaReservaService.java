package com.example.BackEmpresa.reserva.infrastructure.kafka;

import com.example.BackEmpresa.reserva.application.ReservaServiceImpl;
import com.example.BackEmpresa.reserva.domain.ReservaEntity;
import com.example.BackEmpresa.reserva.infrastructure.controller.dto.ReservaOutputDTO;
import com.example.BackEmpresa.reserva.infrastructure.repository.ReservaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
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
                log.info("exito en la craeción");

            }
            case "eliminar" -> {
                reservaRepository.delete(reservaRepository.findById(reservaOutputDTO.getIdReserva()).orElseThrow());
                log.info("USUARIO ELIMINADO");
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

                log.info("USUARIO ACTUALIZADO");

            }

            default -> {
                log.info("ERROR KAFKA SERVICE RESERVA ! ACCION NO ESPECIFICADA()");
            }
        }
    }
}
