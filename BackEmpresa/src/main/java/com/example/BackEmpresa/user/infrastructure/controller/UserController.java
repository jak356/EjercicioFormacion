package com.example.BackEmpresa.user.infrastructure.controller;



import com.example.BackEmpresa.user.application.UserService;
import com.example.BackEmpresa.user.infrastructure.dto.input.UserInputDTO;
import com.example.BackEmpresa.user.infrastructure.dto.output.UserOutputDTO;

import com.example.BackEmpresa.utilidades.authutils.AuthUtils;
import com.example.BackEmpresa.utilidades.exceptions.UnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v0-empresa/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("adduser")
    public UserOutputDTO addUser(@RequestBody UserInputDTO user) {
        UserOutputDTO userOutputDTO = userService.addUser(user);
        return userOutputDTO;
    }

    @GetMapping("getid/{id}")
    public UserOutputDTO findById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @GetMapping("/getemail/{email}")
    public UserOutputDTO findByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/getall")
    public List<UserOutputDTO> findAll() {
        return userService.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable UUID id,@RequestHeader("Authorization") String authorize) {
        UUID idToken = AuthUtils.getId(authorize);
        if(idToken.equals(id)){
            throw new UnprocesableException("La persona no corresponde con la autorizacion");
        }
        if(userService.findByEmail("admin@adminbus.local").getIdUser().equals(id)) {
            throw new UnprocesableException("EL administrador no se puede eliminar");
        }
        userService.deleteUser(id);
    }

    @PutMapping("{id}")
    public UserOutputDTO updateUser(@PathVariable UUID id, @RequestBody UserInputDTO userInputDTO, @RequestHeader("Authorization") String authorize) {
        UUID idToken = AuthUtils.getId(authorize);
        if(idToken.equals(id)){
            throw new UnprocesableException("La persona no corresponde con la autorizacion");
        }
        return userService.updateuser(id, userInputDTO);
    }

}
