package com.example.BackWeb.user.application;


import com.example.BackWeb.user.infrastructure.dto.input.UserInputDTO;
import com.example.BackWeb.user.infrastructure.dto.output.UserOutputDTO;

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
