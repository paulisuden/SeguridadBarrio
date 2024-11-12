package com.is.servidor_barrio.business.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.is.servidor_barrio.business.domain.dto.provincia.ProvinciaCreateDto;
import com.is.servidor_barrio.business.domain.dto.provincia.ProvinciaDto;
import com.is.servidor_barrio.business.domain.entity.Provincia;
import com.is.servidor_barrio.business.facade.BaseFacadeImpl;
import com.is.servidor_barrio.business.facade.base.ProvinciaFacade;
import com.is.servidor_barrio.business.logic.service.BaseService;
import com.is.servidor_barrio.business.logic.service.PaisServiceImpl;
import com.is.servidor_barrio.business.mapper.BaseMapper;

@Service
public class ProvinciaFacadeImpl
    extends BaseFacadeImpl<Provincia, ProvinciaDto, ProvinciaCreateDto, ProvinciaCreateDto, Long>
    implements ProvinciaFacade {

  @Autowired
  private PaisServiceImpl paisService;

  public ProvinciaFacadeImpl(BaseService<Provincia, Long> baseService,
      BaseMapper<Provincia, ProvinciaDto, ProvinciaCreateDto, ProvinciaCreateDto> baseMapper) {
    super(baseService, baseMapper);
  }

  public ProvinciaDto save(ProvinciaCreateDto provinciaCreateDto) throws Exception {
    System.out.println(provinciaCreateDto.getPaisId());
    var provinciaEntity = baseMapper.toEntityCreate(provinciaCreateDto);
    var paisEntity = paisService.findById(provinciaCreateDto.getPaisId());
    provinciaEntity.setPais(paisEntity);
    var entityCreated = baseService.save(provinciaEntity);
    return baseMapper.toDTO(entityCreated);
  }

  @Override
  public ProvinciaDto update(Long id, ProvinciaCreateDto provinciaCreateDto) throws Exception {
    var provinciaEntity = baseService.findById(id);
    baseMapper.toUpdate(provinciaEntity, provinciaCreateDto);

    // Asignar el país solo si es necesario
    if (!provinciaEntity.getPais().getId().equals(provinciaCreateDto.getPaisId())) {
      var paisEntity = paisService.findById(provinciaCreateDto.getPaisId());
      provinciaEntity.setPais(paisEntity);
    }

    var updatedEntity = baseService.update(id, provinciaEntity);
    return baseMapper.toDTO(updatedEntity);
  }

}
