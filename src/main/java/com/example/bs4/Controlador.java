package com.example.bs4;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ConfigurationPropertiesScan
public class Controlador {
  @Autowired Pojo pojo;
  @GetMapping("valores")
  public String getValores()
  {
    return pojo.toString();
  }
  @GetMapping("var3")
  public String getVar3()
  {

    return "valor de var3 es:" + pojo.getVAR3();
  }

}
