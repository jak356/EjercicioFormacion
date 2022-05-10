package com.example.DBA1.Persona;

import com.example.DBA1.domain.PersonaEntity;
import com.example.DBA1.dto.PersonaOutPutDTO;

import java.util.HashMap;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepo extends JpaRepository<PersonaEntity, Integer> {
   List<PersonaOutPutDTO> findByName(String name);
   void deleteById(Integer id);
   List<PersonaEntity> getData(HashMap<String,Object> conditions);
}
