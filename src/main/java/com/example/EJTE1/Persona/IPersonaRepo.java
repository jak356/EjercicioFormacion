package com.example.EJTE1.Persona;

import com.example.EJTE1.domain.PersonaEntity;
import com.example.EJTE1.dto.PersonaOutPutDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonaRepo extends JpaRepository<PersonaEntity, Integer> {
    List<PersonaOutPutDTO> findByName(String name);
}
