package com.spring.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.spring.app")
@EntityScan("com.spring.app.model")
@EnableJpaRepositories("com.spring.app.dao")
public class SportyShoeApplication3Application {

	public static void main(String[] args) {
		SpringApplication.run(SportyShoeApplication3Application.class, args);
	}

}
