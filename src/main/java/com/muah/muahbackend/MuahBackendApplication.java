package com.muah.muahbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication // disable spring security temporarily
@EnableJpaAuditing
public class MuahBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MuahBackendApplication.class, args);

	}

}
