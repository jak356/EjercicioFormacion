package com.example.bs41;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Bs41Application {
  @Autowired
  Parametros parametros;
  @Autowired
  MiConfiguracion miconfiguracion;
  @Autowired
  Perfiles perfil;

  public static void main(String[] args) {
    SpringApplication.run(Bs41Application.class, args);
  }
  @GetMapping("/parametros")
  public String getParametros() {
    System.out.println(parametros);
    return parametros.toString();
  }

  @PostConstruct
  @GetMapping("/miconfiguracion")
  public String getMiConfiguracion() {
    System.out.println(miconfiguracion);

    return miconfiguracion.toString();
  }

  @GetMapping("/perfil")
  public void getPerfil() {
    perfil.miFuncion();
  }

}
