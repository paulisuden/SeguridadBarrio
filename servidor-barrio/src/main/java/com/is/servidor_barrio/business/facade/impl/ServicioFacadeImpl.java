package com.is.servidor_barrio.business.facade.impl;

import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.dto.pais.PaisCreateDto;
import com.is.servidor_barrio.business.domain.dto.pais.PaisDto;
import com.is.servidor_barrio.business.domain.dto.servicio.ServicioCreateDto;
import com.is.servidor_barrio.business.domain.dto.servicio.ServicioDto;
import com.is.servidor_barrio.business.domain.entity.Servicio;
import com.is.servidor_barrio.business.facade.BaseFacadeImpl;
import com.is.servidor_barrio.business.logic.service.BaseService;
import com.is.servidor_barrio.business.mapper.BaseMapper;

@Service
public class ServicioFacadeImpl extends
    BaseFacadeImpl<Servicio, ServicioDto, ServicioCreateDto, ServicioCreateDto, Long> {

  public ServicioFacadeImpl(BaseService<Servicio, Long> baseService,
      BaseMapper<Servicio, ServicioDto, ServicioCreateDto, ServicioCreateDto> baseMapper) {
    super(baseService, baseMapper);
  }

  @Override
  public ServicioDto save(ServicioCreateDto request) throws Exception {
    System.out.println(request.getNombre());
    var entityToCreate = baseMapper.toEntityCreate(request);
    System.out.println(entityToCreate.getNombre());
    var entityCreated = baseService.save(entityToCreate);
    System.out.println(entityCreated.getNombre());
    return baseMapper.toDTO(entityCreated);
  }

}
