package com.example.BackWeb.correo.infrastructure.kafka;


import com.example.BackWeb.correo.application.CorreoServiceImpl;
import com.example.BackWeb.correo.domain.CorreoEntity;
import com.example.BackWeb.correo.infrastructure.controller.dto.CorreoOutputDTO;
import com.example.BackWeb.correo.infrastructure.repository.CorreoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaCorreoService {
    @Autowired
    CorreoRepository correoRepository;

    @Autowired
    CorreoServiceImpl correoService;

    public void listenTopic(String action, CorreoOutputDTO correoOutputDTO) {
        switch (action) {
            case "crear" -> {
                CorreoEntity correo = correoService.correoOutputDtoToEntity(correoOutputDTO);
                correoRepository.save(correo);

                System.out.println("creado con exito");
            }
            case "eliminar" -> {
                correoRepository.delete(correoRepository.findById(correoOutputDTO.getIdCorreo()).orElseThrow());
                System.out.println("USUARIO ELIMINADO");
            }
            case "update" -> {
               CorreoEntity correo = correoRepository.findById(correoOutputDTO.getIdCorreo()).orElseThrow();

                correo.setCiudadDestino(correoOutputDTO.getCiudadDestino());
                correo.setEmail(correoOutputDTO.getEmail());
                correo.setFechaReserva(correoOutputDTO.getFechaReserva());
                correo.setHoraReserva(correoOutputDTO.getHoraReserva());

                correoRepository.save(correo);

                System.out.println("USUARIO ACTUALIZADO");

            }
            default -> {
                System.out.println("ERROR KAFKA SERVICE CORREO ! ACCION NO ESPECIFICADA");
            }
        }
    }

}
