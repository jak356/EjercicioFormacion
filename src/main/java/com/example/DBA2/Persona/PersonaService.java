package com.example.DBA2.Persona;

import com.example.DBA2.domain.PersonaEntity;
import com.example.DBA2.dto.PersonaInputDTO;
import com.example.DBA2.dto.PersonaOutPutDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService implements IPersona {

@Autowired
  private IPersonaDAL personaRepo;

  @Override
  public void addPersona(PersonaInputDTO personaDTO) throws Exception {
    PersonaEntity personaEntity = new PersonaEntity(personaDTO);
    new PersonaOutPutDTO(personaRepo.addPersona(personaEntity));

  }

  @Override
  public PersonaOutPutDTO findById(Integer id) throws Exception {

     return new PersonaOutPutDTO(personaRepo.findById(id));
    }



  @Override
  public List<PersonaEntity> findAll() { return personaRepo.findAll();}

  @Override
  public void deleteById(Integer id) throws Exception {
  personaRepo.deleteById(id);

  }
}
