package com.example.BS123;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Configuration
public class Bs123Application {
	@Autowired
	private PersonaServiceImp personaServiceImp;
	public static void main(String[] args) {
		SpringApplication.run(Bs123Application.class, args);
	}




}
