package com.example.Ej31.infrastructure.controller;

import com.example.Ej31.ErrorHandlers.NotFoundException;
import com.example.Ej31.ErrorHandlers.UnprocesableException;
import com.example.Ej31.Services.StudentService;
import com.example.Ej31.Services.PersonaService;
import com.example.Ej31.domain.StudentEntity;
import com.example.Ej31.infrastructure.dto.input.StudentInputDTO;
import com.example.Ej31.infrastructure.dto.output.StudentOutputDTO;
import com.example.Ej31.infrastructure.dto.output.StudentSimpleDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
