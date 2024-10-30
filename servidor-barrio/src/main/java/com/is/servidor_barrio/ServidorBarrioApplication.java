package com.is.servidor_barrio;

import com.is.servidor_barrio.business.domain.entity.Pais;
import com.is.servidor_barrio.business.logic.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ServidorBarrioApplication implements CommandLineRunner {

	@Autowired
	private PaisService paisService;

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		SpringApplication.run(ServidorBarrioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Pais pais = new Pais();
		pais.setNombre("Argentina");

		paisService.save(pais);  // Guardar el país en la base de datos
		System.out.println("País guardado correctamente.");
	}
}
