package com.is.servidor_barrio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ServidorBarrioApplication implements CommandLineRunner {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		SpringApplication.run(ServidorBarrioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
