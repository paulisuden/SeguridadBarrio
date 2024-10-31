package com.is.servidor_barrio.business.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.entity.Empleado;
import com.is.servidor_barrio.business.repository.BaseRepository;

@Service
public class EmpleadoServiceImpl extends BaseServiceImpl<Empleado, Long> implements EmpleadoService {
  @Autowired
  public EmpleadoServiceImpl(BaseRepository<Empleado, Long> baseRepository) {
    super(baseRepository);
  }

}
