package com.example.Ej31.Services;

import com.example.Ej31.ErrorHandlers.NotFoundException;
import com.example.Ej31.ErrorHandlers.UnprocesableException;
import com.example.Ej31.Services.interfaces.IEstudios;
import com.example.Ej31.domain.EstudiosEntity;
import com.example.Ej31.infrastructure.dto.input.EstudiosInputDTO;
import com.example.Ej31.infrastructure.dto.output.EstudiosOutputDTO;
import com.example.Ej31.repository.IEstudiosRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudiosService implements IEstudios {

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
