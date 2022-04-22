package com.example.Ej2CRUD;

import com.example.Ej2CRUD.Persona.PersonaService;
import com.example.Ej2CRUD.domain.PersonaEntity;
import com.example.Ej2CRUD.infrastructure.dto.PersonaInputDTO;
import com.example.Ej2CRUD.infrastructure.dto.PersonaOutPutDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persona")
public class ControladorPersona {
  @Autowired
  PersonaService personaService;

@PostMapping("/addPersona")
public PersonaOutPutDTO crearPersona(@RequestBody PersonaInputDTO persona) throws Exception {
  PersonaOutPutDTO personaOutPutDTO = personaService.addPersona(persona);
  return personaOutPutDTO;
}
@GetMapping("/getid/{id}")
  public PersonaOutPutDTO obtenerPorID(@PathVariable Integer id) throws Exception {
  return personaService.findById(id);
}
@GetMapping("/getname/{name}")
 public List<PersonaOutPutDTO> obtenerPorName(@PathVariable String name) throws Exception {
  return personaService.findByName(name);
}
@GetMapping("/getall")
  public List<PersonaEntity> obtenerTodos() {
    return personaService.findAll();
  }
  @DeleteMapping("/delete/{id}")
  public String eleminarPorID(@PathVariable Integer id) throws Exception {
  personaService.deleteById(id);
  return"La id " + id +" eliminada";
  }
}
