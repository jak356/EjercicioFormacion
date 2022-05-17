package com.example.EJS31.estudiante.infrastructure.controller;

import com.example.EJS31.estudiante.application.StudentService;
import com.example.EJS31.estudiante.domain.StudentEntity;
import com.example.EJS31.estudiante.infrastructure.dto.StudentInputDTO;
import com.example.EJS31.estudiante.infrastructure.dto.StudentOutputDTO;
import com.example.EJS31.estudiante.infrastructure.dto.StudentSimpleDTO;
import com.example.EJS31.shared.error.NotFoundException;
import com.example.EJS31.shared.error.UnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiante")
public class ControladorEstudiante {
  @Autowired
  StudentService studentService;

  @PostMapping("/addEstudiante")
  public ResponseEntity<?> crearEstudiante(@RequestBody StudentInputDTO student ) throws UnprocesableException {
      return studentService.addEstudiante(student);
  }
  @GetMapping("/getid/{id}")
  public ResponseEntity<?> obtenerPorId(@PathVariable String id,@RequestParam(defaultValue = "simple") String outputType)
      throws NotFoundException {

   if(outputType.equals("simple")){
     StudentSimpleDTO studentsimple = studentService.findSimpleByID(id);
     return ResponseEntity.ok(studentsimple) ;
   }
   else{
     if(outputType.equals("full")) {
       StudentOutputDTO studentfull = studentService.findById(id);
       return ResponseEntity.ok(studentfull);
     }
     return  ResponseEntity.ok("Estudiante no encontrado");
   }
  }
  @GetMapping("getall")
  public List<StudentEntity> obtenerTodos(){
    return studentService.findAll();
  }
  @DeleteMapping("/delete/{id}")
  public String eliminarPorID(@PathVariable String id) throws NotFoundException {
    studentService.deleteById(id);
    return"La id " + id +" eliminada";
  }
  @PutMapping("addEstudio/{studentID}")
  public StudentOutputDTO crearEstudio(@PathVariable String studentID,@RequestBody List<String> EstudioIDList) throws NotFoundException {
    return studentService.addEstudio(studentID,EstudioIDList);
  }
  @DeleteMapping("removeEstudio/{studentID}")
  public ResponseEntity<?> eliminarEstudio(@PathVariable String studentID,@RequestBody List<String> EstudioIDList) throws NotFoundException {
    return studentService.deleteEstudios(studentID,EstudioIDList);
  }



}
