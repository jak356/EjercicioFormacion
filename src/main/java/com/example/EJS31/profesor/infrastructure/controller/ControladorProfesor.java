package com.example.EJS31.profesor.infrastructure.controller;

import com.example.EJS31.profesor.application.ProfesorService;
import com.example.EJS31.profesor.domain.ProfesorEntity;
import com.example.EJS31.profesor.infrastructure.dto.ProfesorInputDTO;
import com.example.EJS31.profesor.infrastructure.dto.ProfesorOutputDTO;
import com.example.EJS31.shared.error.NotFoundException;
import com.example.EJS31.shared.error.UnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesor")
public class ControladorProfesor {

  @Autowired
  ProfesorService profesorService;

  @PostMapping("/addProfesor")
  public ProfesorOutputDTO crearProfesor(@RequestBody ProfesorInputDTO profesor)
      throws UnprocesableException {
    ProfesorOutputDTO profesorOutputDTO = profesorService.addProfesor(profesor);
    return profesorOutputDTO;
  }

  @GetMapping("/getid/{id}")
  public ProfesorOutputDTO obtenerPorID(@PathVariable String id) throws NotFoundException {
    return profesorService.findById(id);
  }

  @GetMapping("/getall")
  public List<ProfesorEntity> obtenerTodos() {
    return profesorService.findAll();
  }

  @DeleteMapping("/delete/{id}")
  public String eleminarPorID(@PathVariable String id) throws NotFoundException {
    profesorService.deleteById(id);
    return "La id " + id + " eliminada";
  }
}
