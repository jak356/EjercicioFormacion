package com.example.rs1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persona")
public class PersonaController {


  //private final AtomicInteger id = new AtomicInteger();
  @Autowired
  PersonaService personaService;

  @PostMapping("/addPersona")
  public Persona addPersona(@RequestBody Persona persona){
    personaService.setNombre(persona.getNombre());
    personaService.setPoblacion(persona.getPoblacion());
    personaService.setEdad(persona.getEdad());
    personaService.setId(persona.getId());
    personaService.addPersona(persona);
    return personaService.getPersona();

  }

  @PutMapping("/putPersona")
  public Persona actualizarPersona(@RequestBody Persona persona){
    personaService.setNombre(persona.getNombre());
    personaService.setPoblacion(persona.getPoblacion());
    personaService.setEdad(persona.getEdad());
    personaService.setId(persona.getId());
    addPersona(persona);
    return personaService.getPersona();
  }

  @DeleteMapping("/deletePersona/{id}")
  public String deletePersona(@PathVariable(value = "id") int id) throws IndexOutOfBoundsException{
    Persona persona = (personaService.getListaPersona()).stream().filter(p -> p.getId() == id).toList().get(0);
    personaService.deletePersona(persona);
    return "id:" + persona.getId() +" ,nombre:"+persona.getNombre() + " eliminado'a";
  }

  @GetMapping("/getPersona/{id}")
  public Persona getPersona(@PathVariable(value = "id") int id){
    return personaService.getListaPersona().stream().filter(p -> p.getId() == id).toList().get(0);
  }





}