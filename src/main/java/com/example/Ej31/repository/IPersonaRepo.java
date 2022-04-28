package com.example.Ej31.repository;

import com.example.Ej31.domain.PersonaEntity;
import com.example.Ej31.infrastructure.dto.output.PersonaOutPutDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepo extends JpaRepository<PersonaEntity, String> {

  List<PersonaOutPutDTO> findByName(String name);

}
