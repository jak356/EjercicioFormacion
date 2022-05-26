package com.example.EJTE1;

import com.example.EJTE1.ErrorHandlers.NotFoundException;
import com.example.EJTE1.ErrorHandlers.UnprocesableException;
import com.example.EJTE1.Persona.PersonaService;
import com.example.EJTE1.domain.PersonaEntity;
import com.example.EJTE1.dto.PersonaInputDTO;
import com.example.EJTE1.dto.PersonaOutPutDTO;
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
    public PersonaOutPutDTO obtenerPorID(@PathVariable Integer id) throws NotFoundException {
        return personaService.findById(id);
    }

    @GetMapping("/getname/{name}")
    public List<PersonaOutPutDTO> obtenerPorName(@PathVariable String name) throws NotFoundException {

        return personaService.findByName(name);

    }

    @GetMapping("/getall")
    public List<PersonaOutPutDTO> obtenerTodos() {
        return personaService.findAll();
    }

    @DeleteMapping("/delete/{id}")

    public PersonaEntity eleminarPorID(@PathVariable Integer id) throws NotFoundException {
        return personaService.deleteById(id);

    }
}
