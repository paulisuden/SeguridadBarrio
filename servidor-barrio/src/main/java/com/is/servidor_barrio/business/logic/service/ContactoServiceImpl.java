package com.is.servidor_barrio.business.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.entity.Contacto;
import com.is.servidor_barrio.business.repository.BaseRepository;

@Service
public class ContactoServiceImpl extends BaseServiceImpl<Contacto, String> implements ContactoService {
  @Autowired
  public ContactoServiceImpl(BaseRepository<Contacto, String> baseRepository) {
    super(baseRepository);
  }

}
