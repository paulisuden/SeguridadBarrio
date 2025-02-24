package com.is.servidor_barrio.business.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.entity.Empleado;
import com.is.servidor_barrio.business.repository.BaseRepository;

@Service
public class EmpleadoServiceImpl extends BaseServiceImpl<Empleado, String> implements EmpleadoService {
  @Autowired
  public EmpleadoServiceImpl(BaseRepository<Empleado, String> baseRepository) {
    super(baseRepository);
  }

}
