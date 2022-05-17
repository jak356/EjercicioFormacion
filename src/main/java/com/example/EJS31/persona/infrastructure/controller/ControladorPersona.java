package com.example.EJS31.persona.infrastructure.controller;

import com.example.EJS31.persona.application.PersonaService;
import com.example.EJS31.persona.domain.PersonaEntity;
import com.example.EJS31.persona.infrastructure.dto.PersonaInputDTO;
import com.example.EJS31.persona.infrastructure.dto.PersonaOutPutDTO;
import com.example.EJS31.shared.error.NotFoundException;
import com.example.EJS31.shared.error.UnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class ControladorPersona {
  @Autowired
  PersonaService personaService;

@PostMapping("/addPersona")
public PersonaOutPutDTO crearPersona(@RequestBody PersonaInputDTO persona) throws UnprocesableException {
  PersonaOutPutDTO personaOutPutDTO = personaService.addPersona(persona);
  return personaOutPutDTO;
}
@GetMapping("/getid/{id}")
  public PersonaOutPutDTO obtenerPorID(@PathVariable String id) throws NotFoundException {
 return personaService.findById(id);
}
@GetMapping("/getname/{name}")
 public List<PersonaOutPutDTO> obtenerPorName(@PathVariable String name) throws NotFoundException  {

    return personaService.findByName(name);

}
@GetMapping("/getall")
  public List<PersonaEntity> obtenerTodos() {
    return personaService.findAll();
  }
  @DeleteMapping("/delete/{id}")
  public String eleminarPorID(@PathVariable String id) throws NotFoundException {
  personaService.deleteById(id);
  return"La id " + id +" eliminada";
  }

  }

