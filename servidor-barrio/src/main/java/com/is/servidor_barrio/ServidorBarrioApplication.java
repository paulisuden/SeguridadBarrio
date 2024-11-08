package com.is.servidor_barrio;

import com.is.servidor_barrio.business.domain.entity.*;
import com.is.servidor_barrio.business.domain.enumeration.TipoContacto;
import com.is.servidor_barrio.business.domain.enumeration.TipoTelefono;
import com.is.servidor_barrio.business.logic.service.ContactoService;
import com.is.servidor_barrio.business.logic.service.EnvioCorreoService;
import com.is.servidor_barrio.business.logic.service.HabitanteService;
import com.is.servidor_barrio.business.logic.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

import static com.is.servidor_barrio.business.domain.enumeration.TipoVisita.DELIVERY;

@SpringBootApplication
public class ServidorBarrioApplication implements CommandLineRunner {

	@Autowired
	private EnvioCorreoService enviarMail;

	@Autowired
	private ContactoService contactoService;

	@Autowired
	private HabitanteService habitanteService;


	public static void main(String[] args) {
		SpringApplication.run(ServidorBarrioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		Habitante habitante = new Habitante();
		Visitante visitante = new Visitante();
		ContactoEmail contactoEmail = new ContactoEmail();
		ContactoTelefonico contactoTelefonico = new ContactoTelefonico();

		contactoEmail.setEmail("paulisuden2@gmail.com");
		contactoEmail.setTipoContacto(TipoContacto.PERSONAL);

		contactoTelefonico.setTelefono("2613068240");
		contactoTelefonico.setTipoTelefono(TipoTelefono.CELULAR);
		contactoTelefonico.setTipoContacto(TipoContacto.LABORAL);

		List<Contacto> contactos = new ArrayList<>();
		contactos.add(contactoEmail);
		contactos.add(contactoTelefonico);

		habitante.setNombre("Pauli");
		habitante.setApellido("Suden");
		habitante.setContactos(contactos);

		contactoService.save(contactoTelefonico);
		contactoService.save(contactoEmail);
		habitanteService.save(habitante);

		visitante.setNombre("Pepito");
		visitante.setApellido("sanchez");
		visitante.setTipoVisita(DELIVERY);

		//enviarMail.sendEmail(habitante, visitante);


		*/

		System.out.println("Running");


	}
}
