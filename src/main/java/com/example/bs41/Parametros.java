package com.example.bs41;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "parametros")
public class Parametros {
  private String url,password;


  public void setUrl(String url) {
    this.url = url;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "Parametros{" +
        "url='" + url + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
