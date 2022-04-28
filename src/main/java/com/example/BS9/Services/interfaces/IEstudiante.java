package com.example.BS9.Services.interfaces;


import com.example.BS9.domain.StudentEntity;
import com.example.BS9.infrastructure.dto.input.StudentInputDTO;
import com.example.BS9.infrastructure.dto.output.StudentOutputDTO;
import com.example.BS9.infrastructure.dto.output.StudentSimpleDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface IEstudiante {

  ResponseEntity<?> addEstudiante(StudentInputDTO studentDTO) throws Exception;

  StudentOutputDTO findById(String id) throws Exception;

  List<StudentEntity> findAll();

  String deleteById(String id) throws Exception;

  StudentSimpleDTO findSimpleByID(String id) throws Exception;

  StudentOutputDTO addEstudio(String studentID, List<String> EstudioIDList) throws Exception;

  ResponseEntity<?> deleteEstudios(String studentID, List<String> EstudioIDList) throws Exception;

}
