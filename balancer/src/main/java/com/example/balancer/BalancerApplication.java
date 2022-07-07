package com.example.balancer;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication @EnableEurekaClient
public class BalancerApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(BalancerApplication.class).web(WebApplicationType.REACTIVE).run(args);
	}

}
