package com.example.BackWeb.reserva.application;

import com.example.BackWeb.reserva.domain.ReservaEntity;
import com.example.BackWeb.reserva.infrastructure.controller.dto.ReservaDisponibleOutputDTO;
import com.example.BackWeb.reserva.infrastructure.controller.dto.ReservaInputDTO;
import com.example.BackWeb.reserva.infrastructure.controller.dto.ReservaOutputDTO;
import com.example.BackWeb.reserva.infrastructure.repository.ReservaRepository;
import com.example.BackWeb.utilidades.ErrorOutputDto;
import com.example.BackWeb.utilidades.exceptions.UnprocesableException;
import com.example.BackWeb.utilidades.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class ReservaServiceImpl implements ReservaService {
    @Autowired
    ReservaRepository reservaRepository;
    @Autowired
    KafkaProducer sender;
    @Value("${topic}")
    String topic;
    @Value("${server.port}")
    String port;
    String RESERVA = "reserva";
    @Override
    public List<ReservaOutputDTO> getAllReserva() {
        List<ReservaEntity> reserva = reservaRepository.findAll();
        return reserva.stream().map(ReservaOutputDTO::new).toList();
    }

    @Override
    public ReservaOutputDTO filterReservaById(UUID id) {
        ReservaEntity reserva = reservaRepository.findById(id).orElseThrow();
        return EntityToOutputDto(reserva);
    }

    @Override
    public ReservaOutputDTO updateReserva(UUID id, ReservaInputDTO reservaInputDTO) {
        if (reservaRepository.findByEmail(reservaInputDTO.getEmail()) == null) {
            ReservaEntity reserva = reservaRepository.findById(id).orElseThrow();
            reserva.setCiudadDestino(reservaInputDTO.getCiudadDestino());
            reserva.setNombre(reservaInputDTO.getNombre());
            reserva.setTelefono(reservaInputDTO.getTelefono());
            reserva.setEmail(reservaInputDTO.getEmail());
            reserva.setFechaSalida(reservaInputDTO.getFechaSalida());
            reserva.setHoraSalida(reservaInputDTO.getHoraSalida());

            reservaRepository.save(reserva);
            ReservaOutputDTO reservaOutputDTO = EntityToOutputDto(reserva);
            sender.sendMessage(topic, reservaOutputDTO, port, "update", RESERVA);

            return new ReservaOutputDTO(reserva);
        }
        throw new UnprocesableException("Reserva con email" + reservaInputDTO.getEmail()+"ya existe");
    }

    @Override
    public void deleteReserva(UUID id) {
        ReservaOutputDTO reservaOutputDTO = EntityToOutputDto(reservaRepository.findById(id).orElseThrow());
        reservaRepository.delete(reservaRepository.findById(id).orElseThrow());
        sender.sendMessage(topic,reservaOutputDTO,port,"eliminar",RESERVA);
    }

    @Override
    public List<ReservaOutputDTO> filtroReservas(String ciudadDestino, String fechaSalida, float horaSalida) {
        List<ReservaEntity> reservas = reservaRepository.findByParams(ciudadDestino,fechaSalida,horaSalida);
        return reservas.stream().map(ReservaOutputDTO::new).toList();
    }
    @Override
    public List<ReservaDisponibleOutputDTO> PlazasLibre(String ciudadDestino,String fechaSalida, float horaSalida) {

        List<ReservaEntity> reservas = reservaRepository.findByParams(ciudadDestino,fechaSalida,horaSalida);
        return reservas.stream().map(ReservaDisponibleOutputDTO::new).toList();
    }


    @Override
    public ResponseEntity<Object> addReserva(ReservaInputDTO reservaInputDTO) {
        if (reservaRepository.findByEmail(reservaInputDTO.getEmail()) == null) {
            ReservaEntity reserva = reservaInputDtoToEntity(reservaInputDTO);
            reserva.setIdReserva(UUID.randomUUID());
            reservaRepository.save(reserva);
            ReservaOutputDTO reservadto = EntityToOutputDto(reserva);
            sender.sendMessage(topic, reservadto, port, "crear", RESERVA);
            return new ResponseEntity<>(reservadto, HttpStatus.OK);
        } else {
            ErrorOutputDto error = new ErrorOutputDto(400, "ya existe el email del usuario", "fatal", new Date());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

        }

    }

    public ReservaEntity reservaInputDtoToEntity(ReservaInputDTO reservaInputDTO) {
        ReservaEntity reserva = new ReservaEntity();
        reserva.setCiudadDestino(reservaInputDTO.getCiudadDestino());
        reserva.setNombre(reservaInputDTO.getNombre());
        reserva.setTelefono(reservaInputDTO.getTelefono());
        reserva.setEmail(reservaInputDTO.getEmail());
        reserva.setFechaSalida(reservaInputDTO.getFechaSalida());
        reserva.setHoraSalida(reservaInputDTO.getHoraSalida());
        Date horaActual = new Date();
        reserva.setFechaReserva(horaActual);
        reserva.setHoraReserva(horaActual.getTime());
        return reserva;
    }

    public ReservaEntity reservaOutputDtoToEntity(ReservaOutputDTO reservaOutputDTO) {
        ReservaEntity reserva = new ReservaEntity();
        reserva.setIdReserva(reservaOutputDTO.getIdReserva());
        reserva.setCiudadDestino(reservaOutputDTO.getCiudadDestino());
        reserva.setNombre(reserva.getNombre());
        reserva.setTelefono(reserva.getTelefono());
        reserva.setEmail(reserva.getEmail());
        reserva.setFechaReserva(reserva.getFechaReserva());
        reserva.setHoraReserva(reserva.getHoraReserva());
        return reserva;
    }

    public ReservaOutputDTO EntityToOutputDto(ReservaEntity reserva) {
        ReservaOutputDTO reservaOutputDTO = new ReservaOutputDTO();
        reservaOutputDTO.setIdReserva(reserva.getIdReserva());
        reservaOutputDTO.setCiudadDestino(reserva.getCiudadDestino());
        reservaOutputDTO.setNombre(reserva.getNombre());
        reservaOutputDTO.setTelefono(reserva.getTelefono());
        reservaOutputDTO.setEmail(reserva.getEmail());
        reservaOutputDTO.setFechaReserva(reserva.getFechaReserva());
        reservaOutputDTO.setHoraReserva(reserva.getHoraReserva());
        return reservaOutputDTO;
    }
    public ReservaDisponibleOutputDTO EntityToDisponibleOutputDto(ReservaEntity reserva) {
        ReservaDisponibleOutputDTO reservaDisponibleOutputDTO = new ReservaDisponibleOutputDTO();
        reservaDisponibleOutputDTO.setCiudadDestino(reservaDisponibleOutputDTO.getCiudadDestino());
        reservaDisponibleOutputDTO.setFechaSalida(reservaDisponibleOutputDTO.getFechaSalida());
        reservaDisponibleOutputDTO.setHoraSalida(reservaDisponibleOutputDTO.getHoraSalida());
        reservaDisponibleOutputDTO.setNumeroPlazas(reservaDisponibleOutputDTO.getNumeroPlazas());
        return reservaDisponibleOutputDTO;

    }


}
