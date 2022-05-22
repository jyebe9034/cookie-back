package com.example.cookie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class CookieApplication {

	public static void main(String[] args) {
		SpringApplication.run(CookieApplication.class, args);
	}

}
