package com.is.servidor_barrio.business.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.entity.MovimientoVisita;
import com.is.servidor_barrio.business.repository.BaseRepository;

@Service
public class MovimientoVisitaServiceImpl extends BaseServiceImpl<MovimientoVisita, String>
    implements MovimientoVisitaService {

  @Autowired
  public MovimientoVisitaServiceImpl(BaseRepository<MovimientoVisita, String> baseRepository) {
    super(baseRepository);
  }

}
