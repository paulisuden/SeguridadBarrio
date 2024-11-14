package com.is.servidor_barrio.business.logic.service;

import com.is.servidor_barrio.business.domain.entity.Contacto;
import com.is.servidor_barrio.business.domain.entity.ContactoEmail;
import com.is.servidor_barrio.business.domain.entity.Persona;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

public interface PersonaService extends BaseService<Persona, String> {
    public ContactoEmail obtenerContactoEmail(List<Contacto> contactos);
    public Persona findByUsuarioId(String id);

}
