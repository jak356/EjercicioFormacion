package com.example.bs4;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Bs4Application {
  @Autowired
  private Pojo pojo;

  public static void main(String[] args) {
    SpringApplication.run(Bs4Application.class, args);
  }

  @PostConstruct
  private void postInit()
  {
    System.out.println(pojo);
  }

}
