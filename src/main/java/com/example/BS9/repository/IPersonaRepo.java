package com.example.BS9.repository;

import com.example.BS9.domain.PersonaEntity;
import com.example.BS9.infrastructure.dto.output.PersonaOutPutDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepo extends JpaRepository<PersonaEntity, String> {

  List<PersonaOutPutDTO> findByName(String name);

}
