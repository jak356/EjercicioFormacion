package com.example.EJS31.estudiante.application;

import com.example.EJS31.estudiante.domain.StudentEntity;
import com.example.EJS31.estudiante.infrastructure.dto.StudentInputDTO;
import com.example.EJS31.estudiante.infrastructure.dto.StudentOutputDTO;
import com.example.EJS31.estudiante.infrastructure.dto.StudentSimpleDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IStudentService {
    ResponseEntity<?> addEstudiante(StudentInputDTO studentDTO) throws Exception;

    StudentOutputDTO findById(String id) throws Exception;

    List<StudentEntity> findAll();

    String deleteById(String id) throws Exception;

    StudentSimpleDTO findSimpleByID(String id) throws Exception;

    StudentOutputDTO addEstudio(String studentID, List<String> EstudioIDList) throws Exception;

    ResponseEntity<?> deleteEstudios(String studentID, List<String> EstudioIDList) throws Exception;
}
