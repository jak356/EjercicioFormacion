package com.example.Ej2Record;

import com.example.Ej2Record.ErrorHandlers.NotFoundException;
import com.example.Ej2Record.ErrorHandlers.UnprocesableException;
import com.example.Ej2Record.Persona.PersonaService;
import com.example.Ej2Record.domain.PersonaEntity;
import com.example.Ej2Record.dto.PersonaDTO;

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
public PersonaDTO crearPersona(@RequestBody PersonaDTO persona) throws UnprocesableException {
  PersonaDTO personaOutPutDTO = personaService.addPersona(persona);
  return personaOutPutDTO;
}
@GetMapping("/getid/{id}")
  public PersonaDTO obtenerPorID(@PathVariable Integer id) throws NotFoundException  {
 return personaService.findById(id);
}
@GetMapping("/getname/{name}")
 public List<PersonaDTO> obtenerPorName(@PathVariable String name) throws NotFoundException  {

    return personaService.findByName(name);

}
@GetMapping("/getall")
  public List<PersonaEntity> obtenerTodos() {
    return personaService.findAll();
  }
  @DeleteMapping("/delete/{id}")
  public String eleminarPorID(@PathVariable Integer id) throws NotFoundException {
  personaService.deleteById(id);
  return"La id " + id +" eliminada";
  }
}
