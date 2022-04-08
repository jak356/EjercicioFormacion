package com.example.rs1;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImp implements PersonaService {

Persona persona = new Persona();
List<Persona> personas = new ArrayList<>();
  @Override
  public int getId() {
    return persona.getId() ;
  }

  @Override
  public void setId(int id) {
  persona.setId(id);
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
  public String getNombre() {
    return persona.getNombre();
  }

  @Override
  public void setNombre(String nombre) {
  persona.setNombre(nombre);
  }

  @Override
  public String getPoblacion() {
    return persona.getPoblacion();
  }

  @Override
  public void setPoblacion(String poblacion) {
  persona.setPoblacion(poblacion);
  }

  @Override
  public Persona getPersona() {
    return this.persona;
  }
  @Override
  public void addPersona(Persona persona)
  {
     getListaPersona().add(persona);
  }
  @Override
  public void deletePersona(Persona persona)
  {
    getListaPersona().remove(persona);
  }
  @Override
  public List<Persona>getListaPersona()
  {
    return personas;
  }

}
