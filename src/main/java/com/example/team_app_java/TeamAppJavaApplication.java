package com.example.team_app_java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TeamAppJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamAppJavaApplication.class, args);
	}

}
