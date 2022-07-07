package com.example.BackWeb.correo.application;



import com.example.BackWeb.correo.domain.CorreoEntity;
import com.example.BackWeb.correo.infrastructure.controller.dto.CorreoInputDTO;
import com.example.BackWeb.correo.infrastructure.controller.dto.CorreoOutputDTO;
import com.example.BackWeb.correo.infrastructure.repository.CorreoRepository;
import com.example.BackWeb.utilidades.exceptions.UnprocesableException;


import com.example.BackWeb.utilidades.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CorreoServiceImpl implements CorreoService {
    @Autowired
    CorreoRepository correoRepository;
    @Autowired
    KafkaProducer sender;
    @Value("${topic}")
    String topic;
    @Value("${server.port}")
    String port;
    String CORREO = "correo";

    @Override
    public ResponseEntity<List<CorreoEntity>> findAll() {
        List<CorreoEntity> correo = correoRepository.findAll();
        return new ResponseEntity<>(correo.stream().toList(), HttpStatus.OK);
    }

    @Override
    public CorreoEntity filterCorreoById(UUID id) {
        return correoRepository.findById(id).orElseThrow();
    }

    @Override
    public CorreoOutputDTO addCorreo(CorreoInputDTO correoInputDTO) {
        if (correoRepository.findByEmail(correoInputDTO.getEmail()) == null) {
            CorreoEntity correo = correoInputDtoToEntity(correoInputDTO);
            correo.setIdCorreo(UUID.randomUUID());
            correoRepository.save(correo);
            CorreoOutputDTO correodto = EntityToOutputDto(correo);
            sender.sendMessage(topic, correodto, port, "crear", CORREO);
            return correodto;
        }
        throw new UnprocesableException("Correo con email: " + correoInputDTO.getEmail() + " ya existe.");

    }
    public CorreoEntity correoInputDtoToEntity(CorreoInputDTO correoInputDTO) {
        CorreoEntity correo = new CorreoEntity();
        correo.setCiudadDestino(correoInputDTO.getCiudadDestino());
        correo.setEmail(correoInputDTO.getEmail());
        correo.setFechaReserva(correoInputDTO.getFechaReserva());
        correo.setHoraReserva(correoInputDTO.getHoraReserva());
        return correo;
    }

    public CorreoEntity correoOutputDtoToEntity(CorreoOutputDTO correoOutputDTO) {
        CorreoEntity correo = new CorreoEntity();
        correo.setIdCorreo(correoOutputDTO.getIdCorreo());
        correo.setCiudadDestino(correoOutputDTO.getCiudadDestino());
        correo.setEmail(correo.getEmail());
        correo.setFechaReserva(correo.getFechaReserva());
        correo.setHoraReserva(correo.getHoraReserva());
        return correo;
    }

    public CorreoOutputDTO EntityToOutputDto(CorreoEntity correo) {
        CorreoOutputDTO correoOutputDTO = new CorreoOutputDTO();
        correoOutputDTO.setIdCorreo(correo.getIdCorreo());
        correoOutputDTO.setCiudadDestino(correo.getCiudadDestino());
        correoOutputDTO.setEmail(correo.getEmail());
        correoOutputDTO.setFechaReserva(correo.getFechaReserva());
        correoOutputDTO.setHoraReserva(correo.getHoraReserva());
        return correoOutputDTO;
    }
}
