package com.example.DBA2.Persona;

import com.example.DBA2.domain.PersonaEntity;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

public class IPersonaDALImpl implements IPersonaDAL{
    private final MongoTemplate mongoTemplate;

    public IPersonaDALImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public PersonaEntity addPersona(PersonaEntity persona) throws Exception {
        mongoTemplate.save(persona);
        return persona;
    }

    @Override
    public PersonaEntity findById(Integer id) throws Exception {
        return  mongoTemplate.findById(id,PersonaEntity.class);
    }


    @Override
    public List<PersonaEntity> findAll() {
       return mongoTemplate.findAll(PersonaEntity.class);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        PersonaEntity personaEntity = mongoTemplate.findById(id, PersonaEntity.class);
        if(personaEntity != null) mongoTemplate.remove(personaEntity);

    }
}
