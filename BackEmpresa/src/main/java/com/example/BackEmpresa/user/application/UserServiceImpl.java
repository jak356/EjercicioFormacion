package com.example.BackEmpresa.user.application;

import com.example.BackEmpresa.user.domain.UserEntity;
import com.example.BackEmpresa.user.infrastructure.dto.input.UserInputDTO;
import com.example.BackEmpresa.user.infrastructure.dto.output.UserOutputDTO;
import com.example.BackEmpresa.user.infrastructure.repository.UserRepository;
import com.example.BackEmpresa.utilidades.exceptions.UnprocesableException;
import com.example.BackEmpresa.utilidades.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    KafkaProducer sender;
    @Value("${topic}")
    String topic;
    @Value("${server.port}")
    String port;
    String USER = "user";

    @Override
    public UserOutputDTO addUser(UserInputDTO userInputDTO) {
        if(userRepository.findByEmail(userInputDTO.getEmail()) == null) {
            UserEntity user = userInputDTOToEntity(userInputDTO);
            user.setIdUser(UUID.randomUUID());
            userRepository.save(user);

            UserOutputDTO userOutputDTO = new UserOutputDTO(user);
            sender.sendMessage(topic,userOutputDTO,port,"crear",USER);
            return userOutputDTO;
        }

        throw new UnprocesableException("Usuario con email " +userInputDTO.getEmail()+"ya existe");



    }

    @Override
    public UserOutputDTO findById(UUID id) {
        UserEntity user = userRepository.findById(id).orElseThrow();
        return new UserOutputDTO(user);
    }

    @Override
    public UserOutputDTO findByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);

        return new UserOutputDTO(user);
    }

    @Override
    public List<UserOutputDTO> findAll() {
        return userRepository.findAll().stream().map(UserOutputDTO::new).toList();
    }

    @Override
    public UserOutputDTO updateuser(UUID id, UserInputDTO userInputDTO) {

        if(userRepository.findByEmail(userInputDTO.getEmail()) == null) {
            UserEntity user = userRepository.findById(id).orElseThrow();
            user.setName(userInputDTO.getName());
            user.setEmail(userInputDTO.getEmail());
            user.setPassword(userInputDTO.getPassword());

            userRepository.save(user);
            UserOutputDTO userOutputDTO = EntityToUserOutDTO(user);
            sender.sendMessage(topic,userOutputDTO,port,"update",USER);

            return userOutputDTO;
        }
        throw new UnprocesableException("Usuario con email " +userInputDTO.getEmail()+"ya existe");
    }

    @Override
    public void deleteUser(UUID id) {
        UserOutputDTO userOutputDTO = EntityToUserOutDTO(userRepository.findById(id).orElseThrow());
        userRepository.delete(userRepository.findById(id).orElseThrow());
        sender.sendMessage(topic, userOutputDTO, port, "eliminar", USER);


    }

    public UserEntity userInputDTOToEntity(UserInputDTO userInputDTO) {
        UserEntity user = new UserEntity();
        user.setName(userInputDTO.getName());
        user.setEmail(userInputDTO.getEmail());
        user.setPassword(userInputDTO.getPassword());

        return user;
    }

    public UserEntity userOutputDTOToEntity(UserOutputDTO userOutputDTO) {
        UserEntity user = new UserEntity();
        user.setIdUser(userOutputDTO.getIdUser());
        user.setName(userOutputDTO.getName());
        user.setEmail(userOutputDTO.getEmail());
        user.setPassword(userOutputDTO.getPassword());

        return user;
    }

    public UserOutputDTO EntityToUserOutDTO(UserEntity user) {
        UserOutputDTO userOutputDTO = new UserOutputDTO();
        userOutputDTO.setIdUser(user.getIdUser());
        userOutputDTO.setName(user.getName());
        userOutputDTO.setEmail(user.getEmail());
        userOutputDTO.setPassword(user.getPassword());

        return userOutputDTO;
    }



}
