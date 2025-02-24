package com.is.servidor_barrio.business.facade.impl;

import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.dto.servicio.ServicioCreateDto;
import com.is.servidor_barrio.business.domain.dto.servicio.ServicioDto;
import com.is.servidor_barrio.business.domain.entity.Servicio;
import com.is.servidor_barrio.business.facade.BaseFacadeImpl;
import com.is.servidor_barrio.business.logic.service.BaseService;
import com.is.servidor_barrio.business.mapper.BaseMapper;

@Service
public class ServicioFacadeImpl extends
    BaseFacadeImpl<Servicio, ServicioDto, ServicioCreateDto, ServicioCreateDto, String> {

  public ServicioFacadeImpl(BaseService<Servicio, String> baseService,
      BaseMapper<Servicio, ServicioDto, ServicioCreateDto, ServicioCreateDto> baseMapper) {
    super(baseService, baseMapper);
  }

}
