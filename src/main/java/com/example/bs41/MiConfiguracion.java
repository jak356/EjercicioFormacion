package com.example.bs41;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "configuration")
@Component
@PropertySource("classpath:miconfiguracion.properties")
public class MiConfiguracion {
    private int valor1,valor2;


  public void setValor1(int valor1) {
    this.valor1 = valor1;
  }


  public void setValor2(int valor2) {
    this.valor2 = valor2;
  }

  @Override
  public String toString() {
    return "Miconfiguracion{" +
        "valor1=" + valor1 +
        ", valor2=" + valor2 +
        '}';
  }
}
