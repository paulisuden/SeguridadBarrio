package com.is.servidor_barrio.business.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.dto.direccion.DireccionCreateDto;
import com.is.servidor_barrio.business.domain.dto.direccion.DireccionDto;
import com.is.servidor_barrio.business.domain.entity.Direccion;
import com.is.servidor_barrio.business.facade.BaseFacadeImpl;
import com.is.servidor_barrio.business.logic.service.BaseService;
import com.is.servidor_barrio.business.logic.service.LocalidadServiceImpl;
import com.is.servidor_barrio.business.mapper.BaseMapper;

@Service
public class DireccionFacadeImpl
    extends BaseFacadeImpl<Direccion, DireccionDto, DireccionCreateDto, DireccionCreateDto, Long> {

  @Autowired
  private LocalidadServiceImpl localidadService;

  public DireccionFacadeImpl(BaseService<Direccion, Long> baseService,
      BaseMapper<Direccion, DireccionDto, DireccionCreateDto, DireccionCreateDto> baseMapper) {
    super(baseService, baseMapper);
  }

  public DireccionDto save(DireccionCreateDto DireccionCreateDto) throws Exception {
    var direccionEntity = baseMapper.toEntityCreate(DireccionCreateDto);
    var localidadEntity = localidadService.findById(DireccionCreateDto.getLocalidadId());
    direccionEntity.setLocalidad(localidadEntity);
    var entityCreated = baseService.save(direccionEntity);
    return baseMapper.toDTO(entityCreated);
  }

  @Override
  public DireccionDto update(Long id, DireccionCreateDto DireccionCreateDto) throws Exception {
    var direccionEntity = baseService.findById(id);
    baseMapper.toUpdate(direccionEntity, DireccionCreateDto);

    // Asignar el país solo si es necesario
    if (!direccionEntity.getLocalidad().getId().equals(DireccionCreateDto.getLocalidadId())) {
      var localidadEntity = localidadService.findById(DireccionCreateDto.getLocalidadId());
      direccionEntity.setLocalidad(localidadEntity);
    }

    var updatedEntity = baseService.update(id, direccionEntity);
    return baseMapper.toDTO(updatedEntity);
  }

}
