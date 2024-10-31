package com.is.servidor_barrio;

import com.is.servidor_barrio.business.domain.entity.ContactoEmail;
import com.is.servidor_barrio.business.domain.entity.ContactoTelefonico;
import com.is.servidor_barrio.business.domain.entity.Pais;
import com.is.servidor_barrio.business.enumeration.TipoContacto;
import com.is.servidor_barrio.business.enumeration.TipoTelefono;
import com.is.servidor_barrio.business.logic.service.ContactoEmailService;
import com.is.servidor_barrio.business.logic.service.ContactoTelefonicoService;
import com.is.servidor_barrio.business.logic.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
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
