package com.example.BS2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateBean {
  @Bean("bean1")
  public Persona beanPersona1() {
    PersonaService personaService = new PersonaServiceImp();
    personaService.setNombre("bean1");
    personaService.setPoblacion("Madrid");
    personaService.setEdad(47);
    return personaService.getPersona();

  }
  @Bean("bean2")
  public Persona beanPersona2() {
    PersonaService personaService = new PersonaServiceImp();
    personaService.setNombre("bean2");
    personaService.setPoblacion("Zaragoza");
    personaService.setEdad(18);
    return personaService.getPersona();

  }
  @Bean("bean3")
  public Persona beanPersona3() {
    PersonaService personaService = new PersonaServiceImp();
    personaService.setNombre("bean3");
    personaService.setPoblacion("Albacete");
    personaService.setEdad(31);
    return personaService.getPersona();

  }

}
