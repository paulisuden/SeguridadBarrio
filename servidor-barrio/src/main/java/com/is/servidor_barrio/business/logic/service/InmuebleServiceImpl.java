package com.is.servidor_barrio.business.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.entity.Inmbueble;
import com.is.servidor_barrio.business.repository.BaseRepository;

@Service
public class InmuebleServiceImpl extends BaseServiceImpl<Inmbueble, Long> implements InmuebleService {
  @Autowired
  public InmuebleServiceImpl(BaseRepository<Inmbueble, Long> baseRepository) {
    super(baseRepository);
  }

}
