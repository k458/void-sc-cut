package com.k458.void_es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class VoidEsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoidEsApplication.class, args);
	}

}
