package com.example.rs1;

import java.util.List;

public interface PersonaService {
  int getId();
  void setId(int id);
  int getEdad();
  void setEdad(int edad);
  String getNombre();
  void setNombre(String nombre);
  String getPoblacion();
  void setPoblacion(String poblacion);
  Persona getPersona();
  void addPersona(Persona persona);
  void deletePersona(Persona persona);
  List<Persona> getListaPersona();



}
