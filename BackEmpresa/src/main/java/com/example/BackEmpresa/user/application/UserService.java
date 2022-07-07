package com.example.BackEmpresa.user.application;

import com.example.BackEmpresa.user.infrastructure.dto.input.UserInputDTO;
import com.example.BackEmpresa.user.infrastructure.dto.output.UserOutputDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserOutputDTO addUser(UserInputDTO userInputDTO);
    UserOutputDTO findById(UUID id);
    UserOutputDTO findByEmail(String email);
    List<UserOutputDTO> findAll();
    UserOutputDTO updateuser(UUID id, UserInputDTO userInputDTO);
    void deleteUser(UUID id);

}
