package com.is.servidor_barrio.business.facade.impl;

import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.dto.planillaHoraria.PlanillaHorariaCreateDto;
import com.is.servidor_barrio.business.domain.dto.planillaHoraria.PlanillaHorariaDto;
import com.is.servidor_barrio.business.domain.entity.PlanillaHoraria;
import com.is.servidor_barrio.business.facade.BaseFacadeImpl;
import com.is.servidor_barrio.business.logic.service.BaseService;
import com.is.servidor_barrio.business.mapper.BaseMapper;

@Service
public class PlanillaHorariaFacadeImp extends
    BaseFacadeImpl<PlanillaHoraria, PlanillaHorariaDto, PlanillaHorariaCreateDto, PlanillaHorariaCreateDto, Long> {
  public PlanillaHorariaFacadeImp(BaseService<PlanillaHoraria, Long> baseService,
      BaseMapper<PlanillaHoraria, PlanillaHorariaDto, PlanillaHorariaCreateDto, PlanillaHorariaCreateDto> baseMapper) {
    super(baseService, baseMapper);
  }

}