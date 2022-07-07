package com.example.BackEmpresa.correo.infrastructure.kafka;


import com.example.BackEmpresa.correo.application.CorreoService;
import com.example.BackEmpresa.correo.domain.CorreoEntity;
import com.example.BackEmpresa.correo.infrastructure.controller.dto.CorreoInputDTO;
import com.example.BackEmpresa.correo.infrastructure.controller.dto.CorreoOutputDTO;
import com.example.BackEmpresa.correo.infrastructure.repository.CorreoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaCorreoService {
    @Autowired
    CorreoRepository correoRepository;
    @Autowired
    CorreoService correoService;

    public void listenTopic(String action, CorreoOutputDTO correoOutputDTO) {
        switch (action) {
            case "crear" -> {
                CorreoEntity correo = new CorreoEntity(correoOutputDTO);
                correoRepository.save(correo);

                log.info("creado con exito");
            }
            case "reenviar" -> {
                CorreoEntity correo = new CorreoEntity(correoOutputDTO);
                CorreoInputDTO correodto = new CorreoInputDTO(correo);
                correoService.reenvioCorreo(correodto);

                log.info("reenviado con exito");
            }
            default -> {
                log.info("ERROR KAFKA SERVICE CORREO ! ACCION NO ESPECIFICADA");
            }
        }
    }

}
