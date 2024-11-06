package com.is.servidor_barrio.business.logic.service;

import com.is.servidor_barrio.business.domain.entity.Contacto;
import com.is.servidor_barrio.business.domain.entity.ContactoEmail;
import com.is.servidor_barrio.business.domain.entity.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaService extends BaseService<Persona, Long> {
    public ContactoEmail obtenerContactoEmail(List<Contacto> contactos);

}
