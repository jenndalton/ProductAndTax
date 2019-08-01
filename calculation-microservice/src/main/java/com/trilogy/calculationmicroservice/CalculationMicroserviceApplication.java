package com.trilogy.calculationmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CalculationMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculationMicroserviceApplication.class, args);
	}

}
