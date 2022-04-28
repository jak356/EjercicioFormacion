package com.example.Ej31.infrastructure.controller;

import com.example.Ej31.ErrorHandlers.NotFoundException;
import com.example.Ej31.ErrorHandlers.UnprocesableException;
import com.example.Ej31.Services.EstudiosService;
import com.example.Ej31.domain.EstudiosEntity;
import com.example.Ej31.domain.ProfesorEntity;
import com.example.Ej31.infrastructure.dto.input.EstudiosInputDTO;
import com.example.Ej31.infrastructure.dto.output.EstudiosOutputDTO;
import com.example.Ej31.infrastructure.dto.output.ProfesorOutputDTO;
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
