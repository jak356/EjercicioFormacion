package com.example.EJS31.profesor.application;

import com.example.EJS31.profesor.domain.ProfesorEntity;
import com.example.EJS31.profesor.infrastructure.dto.ProfesorInputDTO;
import com.example.EJS31.profesor.infrastructure.dto.ProfesorOutputDTO;
import com.example.EJS31.profesor.infrastructure.repository.IProfesorRepo;
import com.example.EJS31.shared.error.NotFoundException;
import com.example.EJS31.shared.error.UnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService implements IProfesorService {

  @Autowired
  IProfesorRepo profesorRepo;

  @Override
  public ProfesorOutputDTO addProfesor(ProfesorInputDTO profesorDTO) throws UnprocesableException {

    ProfesorEntity profesorEntity = new ProfesorEntity(profesorDTO);
    profesorRepo.save(profesorEntity);
    return new ProfesorOutputDTO(profesorEntity);

  }

  @Override
  public ProfesorOutputDTO findById(String id) throws NotFoundException {
    ProfesorEntity profesorById = profesorRepo.findById(id).orElseThrow(
        () -> new NotFoundException("No se ha encomtrado al profesor cuyo id es: " + id));
    return new ProfesorOutputDTO(profesorById);
  }


  @Override
  public List<ProfesorEntity> findAll() {
    return profesorRepo.findAll();
  }

  @Override
  public String deleteById(String id) throws NotFoundException {
    profesorRepo.findById(id).orElseThrow(
        () -> new NotFoundException("No se ha encomtrado al profesor cuyo id es: " + id));
    profesorRepo.deleteById(id);
    return "El profesor cuyo  id es " + id + " ha sido borrado";

  }
}
