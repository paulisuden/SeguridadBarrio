package com.is.servidor_barrio.business.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.entity.PlanillaHoraria;
import com.is.servidor_barrio.business.repository.BaseRepository;

@Service
public class PlanillaHorariaServiceImpl extends BaseServiceImpl<PlanillaHoraria, String>
    implements PlanillaHorariaService {
  @Autowired
  public PlanillaHorariaServiceImpl(BaseRepository<PlanillaHoraria, String> baseRepository) {
    super(baseRepository);
  }

}
