package com.is.servidor_barrio.business.facade.impl;

import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.dto.inmueble.InmuebleCreateDto;
import com.is.servidor_barrio.business.domain.dto.inmueble.InmuebleDto;
import com.is.servidor_barrio.business.domain.entity.Inmueble;
import com.is.servidor_barrio.business.facade.BaseFacadeImpl;
import com.is.servidor_barrio.business.logic.service.BaseService;
import com.is.servidor_barrio.business.logic.service.UnidadDeNegocioServiceImpl;
import com.is.servidor_barrio.business.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class InmuebleFacadeImp
    extends BaseFacadeImpl<Inmueble, InmuebleDto, InmuebleCreateDto, InmuebleCreateDto, Long> {

  @Autowired
  private UnidadDeNegocioServiceImpl unidadDeNegocioServiceImpl;

  public InmuebleFacadeImp(BaseService<Inmueble, Long> baseService,
      BaseMapper<Inmueble, InmuebleDto, InmuebleCreateDto, InmuebleCreateDto> baseMapper) {
    super(baseService, baseMapper);
  }

  /* 
  @Override
    public InmuebleDto save(InmuebleCreateDto inmuebleCreateDto) throws Exception {
        
        var inmuebleEntity = baseMapper.toEntityCreate(inmuebleCreateDto);

        var unidadDeNegocioEntity = unidadDeNegocioServiceImpl.findById(inmuebleCreateDto.getIdUnidadDeNegocio());
        inmuebleEntity.setUnidadDeNegocio(unidadDeNegocioEntity);

        var entityCreated = baseService.save(inmuebleEntity);
        return baseMapper.toDTO(entityCreated);
    }


    @Override
    public InmuebleDto update(Long id, InmuebleCreateDto inmuebleCreateDto) throws Exception {
        var inmuebleEntity = baseService.findById(id);
        baseMapper.toUpdate(inmuebleEntity, inmuebleCreateDto);

        //UNIDAD DE NEGOCIO
        if (!inmuebleEntity.getIdUnidadDeNegocio().getId().equals(inmuebleCreateDto.getIdUnidadDeNegocio())) {
        var unidadDeNegocioEntity = unidadDeNegocioServiceImpl.findById(inmuebleCreateDto.getIdUnidadDeNegocio());
        inmuebleEntity.setUnidadDeNegocio(unidadDeNegocioEntity);
        }


        var updatedEntity = baseService.update(id, inmuebleEntity);
        return baseMapper.toDTO(updatedEntity);
    }

  */

}