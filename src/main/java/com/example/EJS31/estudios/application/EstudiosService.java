package com.example.EJS31.estudios.application;

import com.example.EJS31.estudios.domain.EstudiosEntity;
import com.example.EJS31.persona.domain.dto.EstudiosInputDTO;
import com.example.EJS31.persona.domain.dto.EstudiosOutputDTO;
import com.example.EJS31.estudios.infrastructure.repository.IEstudiosRepo;
import com.example.EJS31.shared.error.NotFoundException;
import com.example.EJS31.shared.error.UnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudiosService implements IEstudiosService{
    @Autowired
    IEstudiosRepo estudiosRepo;


    @Override
    public EstudiosOutputDTO addEstudio(EstudiosInputDTO estudiosInputDTO)
            throws UnprocesableException {
        EstudiosEntity estudios = new EstudiosEntity(estudiosInputDTO);
        estudiosRepo.save(estudios);
        return new EstudiosOutputDTO(estudios);

    }

    @Override
    public EstudiosOutputDTO findById(String id) throws NotFoundException {
        EstudiosEntity estudios = estudiosRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado el estudio"));
        return new EstudiosOutputDTO(estudios);
    }

    @Override
    public List<EstudiosEntity> findAll() {
        return estudiosRepo.findAll();
    }

    @Override
    public String deleteById(String id) throws NotFoundException {
        estudiosRepo.deleteById(id);
        return "Se ha borrado la asignatura";
    }
}
