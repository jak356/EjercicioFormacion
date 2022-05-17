package com.example.EJS31.profesor.application;


import com.example.EJS31.profesor.domain.ProfesorEntity;
import com.example.EJS31.profesor.infrastructure.dto.ProfesorInputDTO;
import com.example.EJS31.profesor.infrastructure.dto.ProfesorOutputDTO;

import java.util.List;

public interface IProfesorService {

  ProfesorOutputDTO addProfesor(ProfesorInputDTO profesorDTO) throws Exception;

  ProfesorOutputDTO findById(String id) throws Exception;

  List<ProfesorEntity> findAll();

  String deleteById(String id) throws Exception;

}
