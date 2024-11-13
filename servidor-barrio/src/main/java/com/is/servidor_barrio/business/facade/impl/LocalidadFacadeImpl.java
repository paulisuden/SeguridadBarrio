package com.is.servidor_barrio.business.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.dto.localidad.LocalidadCreateDto;
import com.is.servidor_barrio.business.domain.dto.localidad.LocalidadDto;
import com.is.servidor_barrio.business.domain.entity.Localidad;
import com.is.servidor_barrio.business.facade.BaseFacadeImpl;
import com.is.servidor_barrio.business.logic.service.BaseService;
import com.is.servidor_barrio.business.logic.service.DepartamentoServiceImpl;
import com.is.servidor_barrio.business.mapper.BaseMapper;

@Service
public class LocalidadFacadeImpl
    extends BaseFacadeImpl<Localidad, LocalidadDto, LocalidadCreateDto, LocalidadCreateDto, String> {

  @Autowired
  private DepartamentoServiceImpl departamentoService;

  public LocalidadFacadeImpl(BaseService<Localidad, String> baseService,
      BaseMapper<Localidad, LocalidadDto, LocalidadCreateDto, LocalidadCreateDto> baseMapper) {
    super(baseService, baseMapper);
  }

  public LocalidadDto save(LocalidadCreateDto localidadCreateDto) throws Exception {
    var localidadEntity = baseMapper.toEntityCreate(localidadCreateDto);
    var departamentoEntity = departamentoService.findById(localidadCreateDto.getDepartamentoId());
    localidadEntity.setDepartamento(departamentoEntity);
    var entityCreated = baseService.save(localidadEntity);
    return baseMapper.toDTO(entityCreated);
  }

  @Override
  public LocalidadDto update(String id, LocalidadCreateDto localidadCreateDto) throws Exception {
    var localidadEntity = baseService.findById(id);
    baseMapper.toUpdate(localidadEntity, localidadCreateDto);

    if (!localidadEntity.getDepartamento().getId().equals(localidadCreateDto.getDepartamentoId())) {
      var departamentoEntity = departamentoService.findById(localidadCreateDto.getDepartamentoId());
      localidadEntity.setDepartamento(departamentoEntity);
    }

    var updatedEntity = baseService.update(id, localidadEntity);
    return baseMapper.toDTO(updatedEntity);
  }

}
