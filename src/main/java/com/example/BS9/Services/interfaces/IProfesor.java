package com.example.BS9.Services.interfaces;


import com.example.BS9.domain.ProfesorEntity;
import com.example.BS9.infrastructure.dto.input.ProfesorInputDTO;
import com.example.BS9.infrastructure.dto.output.ProfesorOutputDTO;
import java.util.List;

public interface IProfesor {

  ProfesorOutputDTO addProfesor(ProfesorInputDTO profesorDTO) throws Exception;

  ProfesorOutputDTO findById(String id) throws Exception;

  List<ProfesorEntity> findAll();

  String deleteById(String id) throws Exception;

}
