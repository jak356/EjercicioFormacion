package com.example.BS9.Services.interfaces;

import com.example.BS9.domain.EstudiosEntity;
import com.example.BS9.infrastructure.dto.input.EstudiosInputDTO;
import com.example.BS9.infrastructure.dto.output.EstudiosOutputDTO;
import java.util.List;

public interface IEstudios {

  EstudiosOutputDTO addEstudio(EstudiosInputDTO estudiosInputDTO) throws Exception;

  EstudiosOutputDTO findById(String id) throws Exception;

  List<EstudiosEntity> findAll();

  String deleteById(String id) throws Exception;


}
