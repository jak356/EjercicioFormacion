package com.example.BS123;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImp implements PersonaService {

  Persona persona = new Persona();

  public void setPersona(Persona persona) {
    this.persona = persona;

  }

  public Persona getPersona() {
    return persona;
  }

}

