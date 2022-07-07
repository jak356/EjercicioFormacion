package com.example.BackEmpresa.user.infrastructure.kafka;

import com.example.BackEmpresa.user.application.UserServiceImpl;
import com.example.BackEmpresa.user.domain.UserEntity;
import com.example.BackEmpresa.user.infrastructure.dto.output.UserOutputDTO;
import com.example.BackEmpresa.user.infrastructure.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserServiceImpl userService;

    public void listenTopic(String action, UserOutputDTO userOutputDTO) {
        switch (action) {
            case "crear" -> {
                UserEntity user = userService.userOutputDTOToEntity(userOutputDTO);
                userRepository.save(user);
                log.info("CREADO");
            }
            case "eliminar" -> {
                userRepository.delete(userRepository.findById(userOutputDTO.getIdUser()).orElseThrow());
                log.info("USUARIO ELIMINADO");
            }
            case "update" -> {
                UserEntity user = userRepository.findById(userOutputDTO.getIdUser()).orElseThrow();

                user.setName(userOutputDTO.getName());
                user.setEmail(userOutputDTO.getEmail());
                user.setPassword(userOutputDTO.getPassword());

                userRepository.save(user);

                log.info("USUARIO ACTUALIZADO");

            }
            default -> {
                log.info("ERROR KAFKA SERVICE USER ACCION NO ESPECIFICADA");
            }

        }
    }

}
