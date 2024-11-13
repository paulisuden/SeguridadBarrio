package com.is.servidor_barrio.business.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.entity.Servicio;
import com.is.servidor_barrio.business.repository.BaseRepository;

@Service
public class ServicioServiceImpl extends BaseServiceImpl<Servicio, String>
    implements ServicioService {
  @Autowired
  public ServicioServiceImpl(BaseRepository<Servicio, String> baseRepository) {
    super(baseRepository);
  }

}