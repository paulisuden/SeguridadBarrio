package com.is.servidor_barrio.business.facade.impl;

import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.dto.visitante.VisitanteCreateDto;
import com.is.servidor_barrio.business.domain.dto.visitante.VisitanteDto;
import com.is.servidor_barrio.business.domain.entity.Visitante;
import com.is.servidor_barrio.business.facade.BaseFacadeImpl;
import com.is.servidor_barrio.business.logic.service.BaseService;
import com.is.servidor_barrio.business.mapper.BaseMapper;

@Service
public class VisitanteFacadeImp
    extends BaseFacadeImpl<Visitante, VisitanteDto, VisitanteCreateDto, VisitanteCreateDto, String> {
  public VisitanteFacadeImp(BaseService<Visitante, String> baseService,
      BaseMapper<Visitante, VisitanteDto, VisitanteCreateDto, VisitanteCreateDto> baseMapper) {
    super(baseService, baseMapper);
  }

}
