package com.example.BS3;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Bs3Application {

	public static void main(String[] args) {
		SpringApplication.run(Bs3Application.class, args);
	}

	@PostConstruct
	public void init1() {
		System.out.println("Hola desde la clase inicial");
	}

	@Bean
	CommandLineRunner init2()
	{
		return p -> System.out.println("Hola desde la clase secundaria");
	}


}
