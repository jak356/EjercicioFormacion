package com.example.EJS31.estudios.application;

import com.example.EJS31.estudios.domain.EstudiosEntity;
import com.example.EJS31.persona.domain.dto.EstudiosInputDTO;
import com.example.EJS31.persona.domain.dto.EstudiosOutputDTO;

import java.util.List;

public interface IEstudiosService {
    EstudiosOutputDTO addEstudio(EstudiosInputDTO estudiosInputDTO) throws Exception;

    EstudiosOutputDTO findById(String id) throws Exception;

    List<EstudiosEntity> findAll();

    String deleteById(String id) throws Exception;
}
