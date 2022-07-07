package com.example.BackWeb.auth;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("api/v0")
public class AuthControlador {
    @Value("${urlempresa}")
    String EMPRESA;

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestHeader("email") String email, @RequestHeader("password") String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("email", email);
        headers.set("password", password);
        HttpEntity<Object> request = new HttpEntity<>(headers);
        return new RestTemplate().exchange(
                EMPRESA,
                HttpMethod.POST,
                request,
                String.class);
    }
}
