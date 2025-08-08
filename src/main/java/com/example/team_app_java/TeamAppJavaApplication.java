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

		// DB_USERNAME 읽기
		String username = dotenv.get("DB_USERNAME");
		if (username == null || username.isBlank()) {
			username = System.getenv("DB_USERNAME");
		}

		// DB_PASSWORD 읽기
		String password = dotenv.get("DB_PASSWORD");
		if (password == null || password.isBlank()) {
			password = System.getenv("DB_PASSWORD");
		}

		// 로그로 확인 (배포 환경에서 null 여부 확인용)
		System.out.println("[ENV CHECK] DB_USERNAME: " + username);
		System.out.println("[ENV CHECK] DB_PASSWORD: " + (password != null ? "********" : null));

		// null이 아닐 때만 setProperty 호출
		if (username != null) {
			System.setProperty("DB_USERNAME", username);
		} else {
			System.err.println("ERROR: DB_USERNAME is not set in environment variables!");
		}

		if (password != null) {
			System.setProperty("DB_PASSWORD", password);
		} else {
			System.err.println("ERROR: DB_PASSWORD is not set in environment variables!");
		}

		SpringApplication.run(TeamAppJavaApplication.class, args);
	}


}
