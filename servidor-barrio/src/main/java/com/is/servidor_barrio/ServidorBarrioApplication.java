package com.is.servidor_barrio;

import com.is.servidor_barrio.business.domain.entity.*;
import com.is.servidor_barrio.business.logic.service.EnvioCorreoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.List;

import static com.is.servidor_barrio.business.enumeration.TipoVisita.DELIVERY;

@SpringBootApplication
public class ServidorBarrioApplication implements CommandLineRunner {

	@Autowired
	private EnvioCorreoService enviarMail;

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		SpringApplication.run(ServidorBarrioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Habitante habitante = new Habitante();
		Visitante visitante = new Visitante();

		habitante.setNombre("Pauli");
		habitante.setApellido("Suden");
		habitante.setCorreo("paulisuden2@gmail.com");

		visitante.setNombre("Pepito");
		visitante.setApellido("sanchez");
		visitante.setTipoVisita(DELIVERY);

		enviarMail.sendEmail(habitante, visitante);

	}
}
