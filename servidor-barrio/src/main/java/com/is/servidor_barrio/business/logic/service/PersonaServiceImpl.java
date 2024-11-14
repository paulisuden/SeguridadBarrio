package com.is.servidor_barrio.business.logic.service;

import com.is.servidor_barrio.business.domain.entity.Contacto;
import com.is.servidor_barrio.business.domain.entity.ContactoEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.entity.Persona;
import com.is.servidor_barrio.business.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl extends BaseServiceImpl<Persona, String> implements PersonaService {
  @Autowired
  public PersonaServiceImpl(BaseRepository<Persona, String> baseRepository) {
    super(baseRepository);
  }

  @Override
  public ContactoEmail obtenerContactoEmail(List<Contacto> contactos) {
    try {
      // Iterar sobre la lista de contactos
      for (Contacto contacto : contactos) {
        if (contacto instanceof ContactoEmail) {
          return (ContactoEmail) contacto; // Retorna el primer ContactoEmail encontrado
        }
      }
    } catch (ClassCastException ex) {
      // error de conversión de tipo
      System.err.println("Error de conversión de tipo: " + ex.getMessage());

    } catch (NullPointerException ex) {
      // lista de contactos sea null
      System.err.println("La lista de contactos es null: " + ex.getMessage());

    } catch (Exception ex) {
      System.err.println("Ocurrió un error al obtener el ContactoEmail: " + ex.getMessage());
    }
    return null; // Si no se encuentra un ContactoEmail o si ocurre una excepción
  }

}
