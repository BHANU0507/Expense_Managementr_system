package com.adp.managerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = {"http://localhost:3000/","https://ephemeral-choux-4b34d5.netlify.app/"})
@ComponentScan(basePackages = { "com.adp.managerservice.*" })
@EnableDiscoveryClient
@EnableFeignClients
@Component
@EnableJpaRepositories(basePackages = { "com.adp.managerservice.*" })
@EntityScan(basePackages = { "com.adp.managerservice.*" }) 
public class ManagerserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerserviceApplication.class, args);
	}

}