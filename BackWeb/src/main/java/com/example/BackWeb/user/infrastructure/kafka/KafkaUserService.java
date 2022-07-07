package com.example.BackWeb.user.infrastructure.kafka;

import com.example.BackWeb.user.application.UserService;
import com.example.BackWeb.user.application.UserServiceImpl;
import com.example.BackWeb.user.domain.UserEntity;
import com.example.BackWeb.user.infrastructure.dto.input.UserInputDTO;
import com.example.BackWeb.user.infrastructure.dto.output.UserOutputDTO;
import com.example.BackWeb.user.infrastructure.repository.UserRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
                System.out.println("USUARIO CREADO");
            }
            case "eliminar" -> {
                userRepository.delete(userRepository.findById(userOutputDTO.getIdUser()).orElseThrow());
                System.out.println("USUARIO ELIMINADO");
            }

        }
    }

}
