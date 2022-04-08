package com.example.BS2;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador2")
public class Controlador2 {
  @Autowired
  PersonaService personaService;

  @Autowired
  CiudadService ciudadService;


  //Obtiene objeto persona
  @GetMapping("/getPersona")
  public Persona getPersona()
  {
    personaService.setEdad(personaService.getEdad()*2);
    return personaService.getPersona();

  }
  //Obtiene la lista de ciudades
  @GetMapping("/getCiudad")
  public List<Ciudad> getListaCiudad()
  {
    //ciudadService.getListaCiudad().stream().forEach(System.out::println);
    return ciudadService.getListaCiudad();
  }
  @Autowired
  @Qualifier("bean1")
  Persona persona1;

  @Autowired
  @Qualifier("bean2")
  Persona persona2;

  @Autowired
  @Qualifier("bean3")
  Persona persona3;
  //Obtiene personas dependiendo del numero introducido 1,2,3.
  @GetMapping("/bean/{bean}")
  public Persona getPersonaBean(@PathVariable int bean) {

    switch(bean)
    {
      case 1 : return persona1;
      case 2 : return persona2;
      case 3 : return persona3;
      default : return new Persona();
    }
  }

}
