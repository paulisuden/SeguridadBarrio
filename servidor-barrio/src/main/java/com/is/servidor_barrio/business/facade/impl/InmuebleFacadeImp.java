package com.is.servidor_barrio.business.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.dto.inmueble.InmuebleCreateDto;
import com.is.servidor_barrio.business.domain.dto.inmueble.InmuebleDto;
import com.is.servidor_barrio.business.domain.entity.Inmueble;
import com.is.servidor_barrio.business.facade.BaseFacadeImpl;
import com.is.servidor_barrio.business.logic.service.BaseService;
import com.is.servidor_barrio.business.logic.service.UnidadDeNegocioServiceImpl;
import com.is.servidor_barrio.business.mapper.BaseMapper;

@Service
public class InmuebleFacadeImp
    extends BaseFacadeImpl<Inmueble, InmuebleDto, InmuebleCreateDto, InmuebleCreateDto, String> {

  @Autowired
  private UnidadDeNegocioServiceImpl unidadDeNegocioServiceImpl;

  public InmuebleFacadeImp(BaseService<Inmueble, String> baseService,
      BaseMapper<Inmueble, InmuebleDto, InmuebleCreateDto, InmuebleCreateDto> baseMapper) {
    super(baseService, baseMapper);
  }

  public InmuebleDto save(InmuebleCreateDto inmueblCreateDto) throws Exception {
    var inmuebleEntity = baseMapper.toEntityCreate(inmueblCreateDto);
    var negocioEntity = unidadDeNegocioServiceImpl.findById(inmueblCreateDto.getIdUnidadDeNegocio());
    inmuebleEntity.setUnidadDeNegocio(negocioEntity);
    var entityCreated = baseService.save(inmuebleEntity);
    return baseMapper.toDTO(entityCreated);
  }

  @Override
  public InmuebleDto update(String id, InmuebleCreateDto inmueblCreateDto) throws Exception {
    var inmuebleEntity = baseService.findById(id);
    baseMapper.toUpdate(inmuebleEntity, inmueblCreateDto);

    if (!inmuebleEntity.getUnidadDeNegocio().getId().equals(inmueblCreateDto.getIdUnidadDeNegocio())) {
      var negocioEntity = unidadDeNegocioServiceImpl.findById(inmueblCreateDto.getIdUnidadDeNegocio());
      inmuebleEntity.setUnidadDeNegocio(negocioEntity);
    }

    var updatedEntity = baseService.update(id, inmuebleEntity);
    return baseMapper.toDTO(updatedEntity);
  }




}