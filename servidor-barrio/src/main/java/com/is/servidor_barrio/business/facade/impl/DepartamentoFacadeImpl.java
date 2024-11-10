package com.is.servidor_barrio.business.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.dto.departamento.DepartamentoCreateDto;
import com.is.servidor_barrio.business.domain.dto.departamento.DepartamentoDto;
import com.is.servidor_barrio.business.domain.entity.Departamento;
import com.is.servidor_barrio.business.facade.BaseFacadeImpl;
import com.is.servidor_barrio.business.facade.base.DepartamentoFacade;
import com.is.servidor_barrio.business.logic.service.BaseService;
import com.is.servidor_barrio.business.logic.service.ProvinciaServiceImpl;
import com.is.servidor_barrio.business.mapper.BaseMapper;

@Service
public class DepartamentoFacadeImpl
    extends BaseFacadeImpl<Departamento, DepartamentoDto, DepartamentoCreateDto, DepartamentoCreateDto, Long>
    implements DepartamentoFacade {

  @Autowired
  private ProvinciaServiceImpl serviceImpl;

  public DepartamentoFacadeImpl(BaseService<Departamento, Long> baseService,
      BaseMapper<Departamento, DepartamentoDto, DepartamentoCreateDto, DepartamentoCreateDto> baseMapper) {
    super(baseService, baseMapper);
  }

  public DepartamentoDto save(DepartamentoCreateDto createDto) throws Exception {
    var departamentoEntity = baseMapper.toEntityCreate(createDto);
    var provinciaEntity = serviceImpl.findById(createDto.getProvinciaId());
    departamentoEntity.setProvincia(provinciaEntity);
    var entityCreated = baseService.save(departamentoEntity);
    return baseMapper.toDTO(entityCreated);
  }

  @Override
  public DepartamentoDto update(Long id, DepartamentoCreateDto createDto) throws Exception {
    var departamentoEntity = baseService.findById(id);
    baseMapper.toUpdate(departamentoEntity, createDto);

    if (!departamentoEntity.getProvincia().getId().equals(createDto.getProvinciaId())) {
      var provinciaEntity = serviceImpl.findById(createDto.getProvinciaId());
      departamentoEntity.setProvincia(provinciaEntity);
    }

    var updatedEntity = baseService.update(id, departamentoEntity);
    return baseMapper.toDTO(updatedEntity);
  }

}
