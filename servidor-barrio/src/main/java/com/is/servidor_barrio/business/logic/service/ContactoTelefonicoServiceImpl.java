package com.is.servidor_barrio.business.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.entity.ContactoTelefonico;
import com.is.servidor_barrio.business.repository.BaseRepository;

@Service
public class ContactoTelefonicoServiceImpl extends BaseServiceImpl<ContactoTelefonico, String>
    implements ContactoTelefonicoService {
  @Autowired
  public ContactoTelefonicoServiceImpl(BaseRepository<ContactoTelefonico, String> baseRepository) {
    super(baseRepository);
  }

}
