package com.adp.emsgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EmsGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmsGatewayApplication.class, args);
	}

}