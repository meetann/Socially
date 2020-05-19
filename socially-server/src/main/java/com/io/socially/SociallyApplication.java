package com.io.socially;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.io.socially.Repository")
public class SociallyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SociallyApplication.class, args);
	}

}
