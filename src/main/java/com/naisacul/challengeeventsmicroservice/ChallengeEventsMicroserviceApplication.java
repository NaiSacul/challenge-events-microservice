package com.naisacul.challengeeventsmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ChallengeEventsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeEventsMicroserviceApplication.class, args);
	}

}
