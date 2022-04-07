package com.example.BS123;

import com.example.BS123.Persona;
import com.example.BS123.PersonaService;
import java.io.ObjectInputFilter.Config;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controlador1 {
  @Autowired
  private PersonaServiceImp personaServiceImp;
  @GetMapping("/controlador1")
  public Persona getPersonaControlador()
  {
    Persona persona = new Persona();

    persona.setNombre("Javier");
    persona.setPoblacion("Logroño");
    persona.setEdad(24);

    personaServiceImp.setPersona(persona);
    return personaServiceImp.getPersona();
  }




}
