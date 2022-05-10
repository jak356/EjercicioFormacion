package com.example.SA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.example.SA.infrastructure"})
public class SaApplication {


	public static void main(String[] args) {
		SpringApplication.run(SaApplication.class, args);
	}



}
