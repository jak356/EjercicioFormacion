package com.example.BS9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Bs9Application {

	public static void main(String[] args) {
		SpringApplication.run(Bs9Application.class, args);
	}

}
