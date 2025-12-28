package com.example.skola;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SkolaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkolaApplication.class, args);
	}

}
