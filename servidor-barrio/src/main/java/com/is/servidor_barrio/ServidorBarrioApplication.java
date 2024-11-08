package com.is.servidor_barrio;

import com.is.servidor_barrio.business.domain.entity.Departamento;
import com.is.servidor_barrio.business.logic.service.EnvioCorreoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServidorBarrioApplication implements CommandLineRunner {

	@Autowired
	private EnvioCorreoService enviarMail;


	public static void main(String[] args) {
		SpringApplication.run(ServidorBarrioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		Habitante habitante = new Habitante();
		Visitante visitante = new Visitante();
		ContactoEmail contactoEmail = new ContactoEmail();

		contactoEmail.setEmail("paulisuden2@gmail.com");

		List<Contacto> contactos = new ArrayList<>();
		contactos.add(contactoEmail);

		habitante.setNombre("Pauli");
		habitante.setApellido("Suden");
		habitante.setContactos(contactos);

		visitante.setNombre("Pepito");
		visitante.setApellido("sanchez");
		visitante.setTipoVisita(DELIVERY);

		enviarMail.sendEmail(habitante, visitante);

		*/

		System.out.println("Running");


	}
}
