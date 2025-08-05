package com.example.team_app_java;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Objects;
import java.util.Optional;

@EnableJpaAuditing
@SpringBootApplication
public class TeamAppJavaApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

		// .env가 없어도 system environment 변수 읽기
		String username = dotenv.get("DB_USERNAME");
		if (username == null) {
			username = System.getenv("DB_USERNAME");
		}
		String password = dotenv.get("DB_PASSWORD");
		if (password == null) {
			password = System.getenv("DB_PASSWORD");
		}

		System.setProperty("DB_USERNAME", username);
		System.setProperty("DB_PASSWORD", password);

		SpringApplication.run(TeamAppJavaApplication.class, args);
	}

}
