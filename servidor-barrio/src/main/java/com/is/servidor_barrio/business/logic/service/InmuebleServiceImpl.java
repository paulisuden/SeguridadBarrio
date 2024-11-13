package com.is.servidor_barrio.business.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.entity.Inmueble;
import com.is.servidor_barrio.business.repository.BaseRepository;

@Service
public class InmuebleServiceImpl extends BaseServiceImpl<Inmueble, String> implements InmuebleService {
  @Autowired
  public InmuebleServiceImpl(BaseRepository<Inmueble, String> baseRepository) {
    super(baseRepository);
  }

}
