package com.is.servidor_barrio.business.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.entity.Persona;
import com.is.servidor_barrio.business.repository.BaseRepository;

@Service
public class PersonaServiceImpl extends BaseServiceImpl<Persona, Long> implements PersonaService {
  @Autowired
  public PersonaServiceImpl(BaseRepository<Persona, Long> baseRepository) {
    super(baseRepository);
  }

}
