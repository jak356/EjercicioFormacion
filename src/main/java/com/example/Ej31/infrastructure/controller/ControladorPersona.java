package com.example.Ej31.infrastructure.controller;

import com.example.Ej31.ErrorHandlers.NotFoundException;
import com.example.Ej31.ErrorHandlers.UnprocesableException;
import com.example.Ej31.Services.PersonaService;
import com.example.Ej31.domain.PersonaEntity;
import com.example.Ej31.infrastructure.dto.input.PersonaInputDTO;
import com.example.Ej31.infrastructure.dto.output.PersonaOutPutDTO;
import com.example.Ej31.infrastructure.dto.output.ProfesorOutputDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
  public PersonaOutPutDTO obtenerPorID(@PathVariable String id) throws NotFoundException  {
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

