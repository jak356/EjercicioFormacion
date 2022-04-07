package com.example.BS123;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controlador2 {
  @Autowired
  PersonaServiceImp personaServiceImp;
  @GetMapping("/controlador2/getPersona")
  public Persona addPersona(Persona persona)
  {
    persona = personaServiceImp.getPersona();
    persona.setEdad(persona.getEdad() + 1);
    return persona;
  }

}
