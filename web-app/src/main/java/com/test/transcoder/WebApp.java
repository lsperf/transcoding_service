package com.test.transcoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@EnableAutoConfiguration(exclude = {
	org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class
})
@Configuration
@ComponentScan(basePackages = {"com.test"})
@EnableJpaRepositories("com.test")
@EntityScan("com.test")
public class WebApp {

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	public static void main(String[] args) {
		SpringApplication.run(WebApp.class, args);
	}

}
