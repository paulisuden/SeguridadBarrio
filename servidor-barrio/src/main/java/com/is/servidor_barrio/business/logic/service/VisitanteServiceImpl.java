package com.is.servidor_barrio.business.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.entity.Visitante;
import com.is.servidor_barrio.business.repository.BaseRepository;

@Service
public class VisitanteServiceImpl extends BaseServiceImpl<Visitante, String> implements VisitanteService {
  @Autowired
  public VisitanteServiceImpl(BaseRepository<Visitante, String> baseRepository) {
    super(baseRepository);
  }

}
