package com.example.EJS31.shared.controller;

import com.example.EJS31.persona.application.IPersonaService;
import com.example.EJS31.persona.infrastructure.dto.PersonaOutPutDTO;
import com.example.EJS31.shared.error.NotFoundException;
import com.example.EJS31.shared.error.UnprocesableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ControladorLogin {
    @Autowired
    IPersonaService personaService;
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String pwd)
        throws NotFoundException, UnprocesableException
    {
        List<PersonaOutPutDTO> lista = personaService.findByName(username);
        if(lista.size() == 0) throw new NotFoundException("Usuario " + username+ "no encontrado");
        if (lista.size() > 1) throw new UnprocesableException("Usuario repetido");
        PersonaOutPutDTO pout = lista.get(0);
        if(!pwd.equals(pout.getPassword())) throw new UnprocesableException("Password no valido");
        String rol =pout.getAdmin();
        return new ResponseEntity<>(getJWTToken(username,rol), HttpStatus.OK);

    }
    private String getJWTToken(String username, String rol) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(rol);

        String token = Jwts
                .builder()
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;




    }


}
