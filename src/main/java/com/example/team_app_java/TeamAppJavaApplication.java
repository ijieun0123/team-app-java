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
		try {
			Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

			System.setProperty("DB_USERNAME", Optional.ofNullable(dotenv.get("DB_USERNAME")).orElse(System.getenv("DB_USERNAME")));
			System.setProperty("DB_PASSWORD", Optional.ofNullable(dotenv.get("DB_PASSWORD")).orElse(System.getenv("DB_PASSWORD")));
		} catch (Exception e) {
			// 로그 찍고 무시 (필요하면)
			System.out.println("Dotenv 로딩 실패, 환경변수에서 읽기 시도");
		}

		SpringApplication.run(TeamAppJavaApplication.class, args);
	}

}
