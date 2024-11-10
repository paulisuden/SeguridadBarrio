package com.is.servidor_barrio.business.facade.impl;

import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.dto.inmueble.InmuebleCreateDto;
import com.is.servidor_barrio.business.domain.dto.inmueble.InmuebleDto;
import com.is.servidor_barrio.business.domain.entity.Inmueble;
import com.is.servidor_barrio.business.facade.BaseFacadeImpl;
import com.is.servidor_barrio.business.logic.service.BaseService;
import com.is.servidor_barrio.business.mapper.BaseMapper;

@Service
public class InmuebleFacadeImp
    extends BaseFacadeImpl<Inmueble, InmuebleDto, InmuebleCreateDto, InmuebleCreateDto, Long> {

  public InmuebleFacadeImp(BaseService<Inmueble, Long> baseService,
      BaseMapper<Inmueble, InmuebleDto, InmuebleCreateDto, InmuebleCreateDto> baseMapper) {
    super(baseService, baseMapper);
  }

}