package com.example.BackEmpresa;

import com.example.BackEmpresa.user.application.UserServiceImpl;
import com.example.BackEmpresa.user.infrastructure.dto.input.UserInputDTO;
import com.example.BackEmpresa.user.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackEmpresaApplication {
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackEmpresaApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserServiceImpl userService){
		return args -> {
			if (userRepository.findByEmail("admin@adminbus.local") == null) {
				userService.addUser(new UserInputDTO("Admin", "1234", "admin@adminbus.local"));
			}
		};
	}



}
