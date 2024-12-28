package com.example.DigiMath_frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DigiMathFrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigiMathFrontendApplication.class, args);
	}

}
