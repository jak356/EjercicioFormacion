package com.example.EJTE1.Persona;

import com.example.EJTE1.ErrorHandlers.NotFoundException;
import com.example.EJTE1.ErrorHandlers.UnprocesableException;
import com.example.EJTE1.domain.PersonaEntity;
import com.example.EJTE1.dto.PersonaInputDTO;
import com.example.EJTE1.dto.PersonaOutPutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService implements IPersona {

    @Autowired
    private IPersonaRepo personaRepo;


    @Override
    public PersonaOutPutDTO addPersona(PersonaInputDTO personaDTO) throws UnprocesableException {
        if (personaDTO.getUsuario().length() < 6 || personaDTO.getUsuario().length() > 10) {
            throw new UnprocesableException("El usuario debe tener entre 6 y 10 caracteres");
        } else {
            PersonaEntity personaEntity = new PersonaEntity(personaDTO);
            personaRepo.save(personaEntity);
            PersonaOutPutDTO personaOutPutDTO = new PersonaOutPutDTO(personaEntity);
            return personaOutPutDTO;
        }
    }

    @Override
    public PersonaOutPutDTO findById(Integer id) throws NotFoundException {
        PersonaEntity personById = personaRepo.findById(id).orElseThrow(() -> new NotFoundException("No se ha encomtrado a la persona cuyo id es: " + id));
        return new PersonaOutPutDTO(personById);
    }

    @Override
    public List<PersonaOutPutDTO> findByName(String name) throws NotFoundException {
        try {
            List<PersonaOutPutDTO> personaEntities = personaRepo.findByName(name);
            return personaEntities;
        } catch (Exception e) {
            throw new NotFoundException("No se ha podido encontrar el usuario " + name);
        }
    }

    @Override
    public List<PersonaOutPutDTO> findAll() {
        List<PersonaOutPutDTO> personOutputDTOList = new ArrayList<>();
        for (PersonaEntity person : personaRepo.findAll()) {
            personOutputDTOList.add(new PersonaOutPutDTO(person));
        }
        return personOutputDTOList;
    }

    @Override
    public PersonaEntity deleteById(Integer id) throws NotFoundException {
        PersonaEntity persona = personaRepo.findById(id).orElseThrow(() -> new NotFoundException("No se ha encomtrado a la persona cuyo id es: " + id));
        personaRepo.delete(persona);
        return persona;


    }
}
