package com.example.BackWeb.user.infrastructure.controller;


import com.example.BackWeb.user.application.UserService;
import com.example.BackWeb.user.infrastructure.dto.input.UserInputDTO;
import com.example.BackWeb.user.infrastructure.dto.output.UserOutputDTO;
import com.example.BackWeb.utilidades.authutils.AuthUtils;
import com.example.BackWeb.utilidades.exceptions.UnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/api/v0/user")
public class UserController {
    @Autowired
    UserService userService;
    @Value("${urlempresa}")
    String EMPRESA;

    @PostMapping
    public UserOutputDTO addUser(@RequestBody UserInputDTO user) {
        UserOutputDTO userOutputDTO = userService.addUser(user);
        return userOutputDTO;
    }

    @GetMapping("/getid/{id}")
    public UserOutputDTO findById(@PathVariable UUID id, @RequestHeader("Authorization") String auth) {
        HttpEntity<Object> request = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<Void> re = new RestTemplate().exchange(EMPRESA + "/" + auth, HttpMethod.GET, request, Void.class);

        if (re.getStatusCode() == HttpStatus.OK) {

            UUID idToken = AuthUtils.getId(auth);
            if (!idToken.equals(id)) {
                throw new UnprocesableException("La persona autenticada no corresponde con al persona que quieres leer");
            }
            return userService.findById(id);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Autenticacion incorrecta");
    }
    @PutMapping("{id}")
    public UserOutputDTO updateUser(@PathVariable UUID id, @RequestBody UserInputDTO userInputDTO, @RequestHeader("Authorization") String authorize) {
       HttpEntity<Object> request = new HttpEntity<>(new HttpHeaders());
       ResponseEntity<Void> re =new RestTemplate().exchange(EMPRESA + "/" + authorize, HttpMethod.GET,request,Void.class);

       if(re.getStatusCode() == HttpStatus.OK) {
           UUID idToken = AuthUtils.getId(authorize);
           if(!idToken.equals(id)) {
               System.out.println(idToken);
               System.out.println(id);
               throw  new UnprocesableException("La persona autenticada no corresponde con la persona actualizada");
           }
           return userService.updateuser(id, userInputDTO);
       }
       throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Autenticacion incorrecta");
    }

    @GetMapping("/getemail/{email}")
    public UserOutputDTO findByEmail(@PathVariable String email, @RequestHeader("Authorization") String auth) {
        HttpEntity<Object> request = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<Void> re = new RestTemplate().exchange(EMPRESA + "/" + auth, HttpMethod.GET, request, Void.class);

        if (re.getStatusCode() == HttpStatus.OK) {

            String eToken = AuthUtils.getEmail(auth);
            if (!eToken.equals(email)) {
                throw new UnprocesableException("La persona autenticada no corresponde con al persona que quieres leer");
            }
            return userService.findByEmail(email);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Autenticacion incorrecta");
    }




   /* @GetMapping("/getall")
    public List<UserOutputDTO> findAll() {
        return userService.findAll();
    }
    */

    /*
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable UUID id) {
        userService.deleteUser(id);
    }
    */



}
