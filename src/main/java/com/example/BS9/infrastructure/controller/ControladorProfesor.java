package com.example.BS9.infrastructure.controller;

import com.example.BS9.ErrorHandlers.NotFoundException;
import com.example.BS9.ErrorHandlers.UnprocesableException;
import com.example.BS9.Services.ProfesorService;
import com.example.BS9.domain.ProfesorEntity;
import com.example.BS9.infrastructure.dto.input.ProfesorInputDTO;
import com.example.BS9.infrastructure.dto.output.ProfesorOutputDTO;
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
