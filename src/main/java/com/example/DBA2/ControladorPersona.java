package com.example.DBA2;


import com.example.DBA2.Persona.PersonaService;
import com.example.DBA2.dto.PersonaInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(
        origins = "*",
        methods = {RequestMethod.POST, RequestMethod.GET})
@RestController
@RequestMapping("/persona")
public class ControladorPersona {
  @Autowired
  PersonaService personaService;

@PostMapping("/addPersona")
public void crearPersona(@RequestBody PersonaInputDTO persona) throws Exception {
  personaService.addPersona(persona);

}
@GetMapping("/getid/{id}")
  public ResponseEntity<?> obtenerPorID(@PathVariable Integer id) throws Exception {
  return ResponseEntity.ok(personaService.findById(id));
}

@GetMapping("/getall")
  public ResponseEntity<?> obtenerTodos() {
    return ResponseEntity.ok(personaService.findAll());
  }

@DeleteMapping("/delete/{id}")
  public String eleminarPorID(@PathVariable Integer id) throws Exception {
  personaService.deleteById(id);
  return"La id " + id +" eliminada";
  }
}
