package com.example.DBA1;

import com.example.DBA1.ErrorHandlers.NotFoundException;
import com.example.DBA1.ErrorHandlers.UnprocesableException;
import com.example.DBA1.Persona.PersonaService;
import com.example.DBA1.domain.PersonaEntity;
import com.example.DBA1.dto.PersonaInputDTO;
import com.example.DBA1.dto.PersonaOutPutDTO;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  public PersonaOutPutDTO obtenerPorID(@PathVariable Integer id) throws NotFoundException  {
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
  public String eleminarPorID(@PathVariable Integer id) throws NotFoundException {
  personaService.deleteById(id);
  return"La id " + id +" eliminada";
  }
@GetMapping("/getData")
public ResponseEntity<?> getData(@RequestParam HashMap<String,Object> conditions)
{
  return ResponseEntity.ok(personaService.findByCondiciones(conditions));
}
}
