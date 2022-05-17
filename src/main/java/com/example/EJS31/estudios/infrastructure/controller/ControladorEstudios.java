package com.example.EJS31.estudios.infrastructure.controller;

import com.example.EJS31.estudios.application.EstudiosService;
import com.example.EJS31.estudios.domain.EstudiosEntity;
import com.example.EJS31.persona.domain.dto.EstudiosInputDTO;
import com.example.EJS31.persona.domain.dto.EstudiosOutputDTO;
import com.example.EJS31.shared.error.NotFoundException;
import com.example.EJS31.shared.error.UnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudios")
public class ControladorEstudios {
    @Autowired
    EstudiosService estudiosService;

    @PostMapping("/addEstudio")
    public EstudiosOutputDTO crearEstudios(@RequestBody EstudiosInputDTO estudio)
        throws UnprocesableException {
      EstudiosOutputDTO estudiosOutputDTO = estudiosService.addEstudio(estudio);
      return estudiosOutputDTO;
    }

    @GetMapping("/getid/{id}")
    public EstudiosOutputDTO obtenerPorID(@PathVariable String id) throws NotFoundException {
      return estudiosService.findById(id);
    }

    @GetMapping("/getall")
    public List<EstudiosEntity> obtenerTodos() {
      return estudiosService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public String eleminarPorID(@PathVariable String id) throws NotFoundException {
      estudiosService.deleteById(id);
      return "La id " + id + " eliminada";
    }

}
