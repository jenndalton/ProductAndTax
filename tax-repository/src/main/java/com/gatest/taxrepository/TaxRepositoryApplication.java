package com.gatest.taxrepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TaxRepositoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxRepositoryApplication.class, args);
	}

}