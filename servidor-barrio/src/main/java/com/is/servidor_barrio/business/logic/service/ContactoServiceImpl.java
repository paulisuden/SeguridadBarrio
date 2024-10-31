package com.is.servidor_barrio.business.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.entity.Contacto;
import com.is.servidor_barrio.business.repository.BaseRepository;

@Service
public class ContactoServiceImpl extends BaseServiceImpl<Contacto, Long> implements ContactoService {
  @Autowired
  public ContactoServiceImpl(BaseRepository<Contacto, Long> baseRepository) {
    super(baseRepository);
  }

}