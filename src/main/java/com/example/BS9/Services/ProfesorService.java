package com.example.BS9.Services;

import com.example.BS9.ErrorHandlers.NotFoundException;
import com.example.BS9.ErrorHandlers.UnprocesableException;
import com.example.BS9.Services.interfaces.IProfesor;
import com.example.BS9.domain.ProfesorEntity;
import com.example.BS9.infrastructure.dto.input.ProfesorInputDTO;
import com.example.BS9.infrastructure.dto.output.ProfesorOutputDTO;
import com.example.BS9.repository.IProfesorRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesorService implements IProfesor {

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
