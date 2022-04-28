package com.example.BS9.Services;

import com.example.BS9.ErrorHandlers.NotFoundException;
import com.example.BS9.ErrorHandlers.UnprocesableException;
import com.example.BS9.Services.interfaces.IEstudios;
import com.example.BS9.domain.EstudiosEntity;
import com.example.BS9.infrastructure.dto.input.EstudiosInputDTO;
import com.example.BS9.infrastructure.dto.output.EstudiosOutputDTO;
import com.example.BS9.repository.IEstudiosRepo;
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
