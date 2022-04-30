package com.muah.muahbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class }) // disable spring security temporarily
public class MuahBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MuahBackendApplication.class, args);
	}

}
