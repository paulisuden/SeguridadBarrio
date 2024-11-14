package com.is.servidor_barrio;

import com.is.servidor_barrio.business.domain.entity.*;
import com.is.servidor_barrio.business.domain.enumeration.Rol;
import com.is.servidor_barrio.business.domain.enumeration.TipoContacto;
import com.is.servidor_barrio.business.domain.enumeration.TipoTelefono;
import com.is.servidor_barrio.business.logic.service.ContactoService;
import com.is.servidor_barrio.business.logic.service.EnvioCorreoService;
import com.is.servidor_barrio.business.logic.service.HabitanteService;
import com.is.servidor_barrio.business.logic.service.PersonaService;
import com.is.servidor_barrio.business.logic.service.UsuarioServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static com.is.servidor_barrio.business.domain.enumeration.TipoVisita.DELIVERY;
import com.is.servidor_barrio.business.logic.service.UsuarioService;

@SpringBootApplication
public class ServidorBarrioApplication implements CommandLineRunner {

	@Autowired
	private EnvioCorreoService enviarMail;

	@Autowired
	private ContactoService contactoService;

	@Autowired
	private HabitanteService habitanteService;

	@Autowired private UsuarioService UsuarioService;


	public static void main(String[] args) {
		SpringApplication.run(ServidorBarrioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/**Usuario usuario = new Usuario();
		usuario.setEmail("test@gmail.com");
		usuario.setClave(new BCryptPasswordEncoder().encode("1234"));
		usuario.setRol(Rol.ADMIN);
		UsuarioService.save(usuario);**/

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
