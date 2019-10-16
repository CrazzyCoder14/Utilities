package com.parse.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EmailParseApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {

		SpringApplication.run(EmailParseApplication.class, args);
	}
	/*
	 * @Override public void run(String... args) throws Exception{
	 * 
	 * 
	 * }
	 */

}
