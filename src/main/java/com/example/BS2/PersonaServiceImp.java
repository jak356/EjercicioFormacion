package com.example.BS2;

import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImp implements PersonaService {

  Persona persona = new Persona();
  @Override
  public String getNombre() {
    return persona.getNombre();
  }

  @Override
  public void setNombre(String nombre) {
    persona.setNombre(nombre);
  }
  @Override
  public String getPoblacion()
  {
    return persona.getPoblacion();
  }
  @Override
  public void setPoblacion(String poblacion) {
    persona.setPoblacion(poblacion);
  }


  @Override
  public int getEdad() {
    return persona.getEdad();
  }

  @Override
  public void setEdad(int edad) {
  persona.setEdad(edad);
  }

  @Override
  public Persona getPersona() {
    return this.persona;
  }
}
