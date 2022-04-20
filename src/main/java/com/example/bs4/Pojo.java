package com.example.bs4;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "my")
@ConstructorBinding
public class Pojo {
  private String VAR1;
  private int VAR2 ;
  private String VAR3;

  public Pojo(String VAR1, int VAR2, String VAR3) {
    this.VAR1 = VAR1;
    this.VAR2 = VAR2;
    this.VAR3 = VAR3;
  }

  public void setVAR2(int VAR2) {
    this.VAR2 = VAR2;
  }


  public void setVAR1(String VAR1) {
    this.VAR1 = VAR1;
  }

  public void setVAR3(String VAR3) {
    this.VAR3 = VAR3;
  }

  public String getVAR3() {
    return VAR3;
  }

  @Override
  public String toString() {
    return "valor de la var1 es: " + VAR1 +" valor de my.var2 es:"+ VAR2;
  }
}
