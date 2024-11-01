package com.is.servidor_barrio.business.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.entity.UnidadDeNegocio;
import com.is.servidor_barrio.business.repository.BaseRepository;

@Service
public class UnidadDeNegocioServiceImpl extends BaseServiceImpl<UnidadDeNegocio, Long>
    implements UnidadDeNegocioService {
  @Autowired
  public UnidadDeNegocioServiceImpl(BaseRepository<UnidadDeNegocio, Long> baseRepository) {
    super(baseRepository);
  }

}
