package com.is.servidor_barrio.business.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.dto.unidadDeNegocio.UnidadDeNegocioCreateDto;
import com.is.servidor_barrio.business.domain.dto.unidadDeNegocio.UnidadDeNegocioDto;
import com.is.servidor_barrio.business.domain.entity.UnidadDeNegocio;
import com.is.servidor_barrio.business.facade.BaseFacadeImpl;
import com.is.servidor_barrio.business.logic.service.BaseService;
import com.is.servidor_barrio.business.logic.service.DireccionServiceImpl;
import com.is.servidor_barrio.business.mapper.BaseMapper;

@Service
public class UnidadDeNegocioFacadeImp extends
    BaseFacadeImpl<UnidadDeNegocio, UnidadDeNegocioDto, UnidadDeNegocioCreateDto, UnidadDeNegocioCreateDto, Long> {

  @Autowired
  private DireccionServiceImpl service;

  public UnidadDeNegocioFacadeImp(BaseService<UnidadDeNegocio, Long> baseService,
      BaseMapper<UnidadDeNegocio, UnidadDeNegocioDto, UnidadDeNegocioCreateDto, UnidadDeNegocioCreateDto> baseMapper) {
    super(baseService, baseMapper);
  }

  public UnidadDeNegocioDto save(UnidadDeNegocioCreateDto UnidadDeNegocioCreateDto) throws Exception {
    var negocioEntity = baseMapper.toEntityCreate(UnidadDeNegocioCreateDto);
    var direccionEntity = service.findById(UnidadDeNegocioCreateDto.getDireccionId());
    negocioEntity.setDireccion(direccionEntity);
    var entityCreated = baseService.save(negocioEntity);
    return baseMapper.toDTO(entityCreated);
  }

  @Override
  public UnidadDeNegocioDto update(Long id, UnidadDeNegocioCreateDto UnidadDeNegocioCreateDto) throws Exception {
    var negocioEntity = baseService.findById(id);
    baseMapper.toUpdate(negocioEntity, UnidadDeNegocioCreateDto);

    if (!negocioEntity.getDireccion().getId().equals(UnidadDeNegocioCreateDto.getDireccionId())) {
      var direccionEntity = service.findById(UnidadDeNegocioCreateDto.getDireccionId());
      negocioEntity.setDireccion(direccionEntity);
    }

    var updatedEntity = baseService.update(id, negocioEntity);
    return baseMapper.toDTO(updatedEntity);
  }

}