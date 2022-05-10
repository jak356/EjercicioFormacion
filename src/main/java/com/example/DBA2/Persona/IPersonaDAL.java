package com.example.DBA2.Persona;

import com.example.DBA2.domain.PersonaEntity;
import java.util.List;

public interface IPersonaDAL  {
   PersonaEntity addPersona(PersonaEntity persona) throws Exception;
   PersonaEntity findById(Integer id) throws Exception;
   List<PersonaEntity> findAll();
   void deleteById(Integer id ) throws Exception;
}
