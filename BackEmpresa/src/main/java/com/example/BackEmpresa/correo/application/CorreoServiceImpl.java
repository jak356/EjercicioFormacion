package com.example.BackEmpresa.correo.application;


import com.example.BackEmpresa.correo.domain.CorreoEntity;
import com.example.BackEmpresa.correo.infrastructure.controller.dto.CorreoInputDTO;
import com.example.BackEmpresa.correo.infrastructure.controller.dto.CorreoOutputDTO;
import com.example.BackEmpresa.correo.infrastructure.repository.CorreoRepository;
import com.example.BackEmpresa.reserva.domain.ReservaEntity;
import com.example.BackEmpresa.reserva.infrastructure.controller.dto.ReservaOutputDTO;
import com.example.BackEmpresa.reserva.infrastructure.repository.ReservaRepository;
import com.example.BackEmpresa.utilidades.exceptions.UnprocesableException;
import com.example.BackEmpresa.utilidades.kafka.KafkaProducer;
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
    ReservaRepository reservaRepository;
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

    @Override
    public ResponseEntity<ReservaOutputDTO> reenvioCorreo(CorreoInputDTO correoInputDto) {
        if(correoRepository.findByEmail(correoInputDto.getEmail()) != null)
        {
            ReservaEntity reserva = reservaRepository.findByEmail(correoInputDto.getEmail());
            ReservaOutputDTO reservadto = new ReservaOutputDTO(reserva);
            return new ResponseEntity<>(reservadto,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<CorreoOutputDTO>> filtroCorreo(String ciudadDestino, String fechaReserva, float horaReserva ) {

        List<CorreoOutputDTO> correosFitrados = correoRepository.findByParams(ciudadDestino,fechaReserva,horaReserva).stream().map(CorreoOutputDTO::new).toList();
        return new ResponseEntity<>(correosFitrados, HttpStatus.OK);

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
