package com.example.recaptcha;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class RecaptchaExampleApplication {
	
	public static void main(String[] args) {
		
		new SpringApplicationBuilder(RecaptchaExampleApplication.class)
		.properties("spring.config.location=classpath:/google.yml")
		.run(args);

		//SpringApplication.run(RecaptchaExampleApplication.class, args);
	}

}
