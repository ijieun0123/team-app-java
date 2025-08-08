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

		String username;
		String password;

		// 로컬 환경에서는 .env 읽기
		if (isLocal()) {
			Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
			username = dotenv.get("DB_USERNAME");
			password = dotenv.get("DB_PASSWORD");
		} else {
			// 배포 환경에서는 환경변수 바로 읽기
			username = System.getenv("DB_USERNAME");
			password = System.getenv("DB_PASSWORD");
		}

		System.out.println("[ENV CHECK] DB_USERNAME: " + username);
		System.out.println("[ENV CHECK] DB_PASSWORD: " + (password != null ? "********" : null));

		if (username != null) System.setProperty("DB_USERNAME", username);
		if (password != null) System.setProperty("DB_PASSWORD", password);

		SpringApplication.run(TeamAppJavaApplication.class, args);
	}

	private static boolean isLocal() {
		String env = System.getenv("RAILWAY_ENVIRONMENT");
		return env == null || env.equalsIgnoreCase("local");
	}

}
