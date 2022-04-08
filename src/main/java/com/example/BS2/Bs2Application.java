package com.example.BS2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class Bs2Application {
	@Autowired
	PersonaService personaService;
	@Autowired
	CiudadService ciudadService;

	public static void main(String[] args) {
		SpringApplication.run(Bs2Application.class, args);
	}


}
