package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class Controlador1 {
  public Usuario usuario = new Usuario();

  @GetMapping("/user")
  public String getNombre()
  {
    return "Hola " + usuario.getNombre();
  }
  @PostMapping("/useradd")
  public  Usuario addUsuario ()
  {
    usuario.setNombre("Gonzalo");
    usuario.setPoblacion("Logroño");
    usuario.setEdad(18);
    return usuario;
  }

}
