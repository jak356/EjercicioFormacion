package com.example.Ej31.Services.interfaces;


import com.example.Ej31.domain.ProfesorEntity;
import com.example.Ej31.infrastructure.dto.input.ProfesorInputDTO;
import com.example.Ej31.infrastructure.dto.output.ProfesorOutputDTO;
import java.util.List;

public interface IProfesor {

  ProfesorOutputDTO addProfesor(ProfesorInputDTO profesorDTO) throws Exception;

  ProfesorOutputDTO findById(String id) throws Exception;

  List<ProfesorEntity> findAll();

  String deleteById(String id) throws Exception;

}
