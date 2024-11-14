package com.is.servidor_barrio.business.facade.impl;

import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.dto.pais.PaisCreateDto;
import com.is.servidor_barrio.business.domain.dto.pais.PaisDto;
import com.is.servidor_barrio.business.domain.entity.Pais;
import com.is.servidor_barrio.business.facade.BaseFacadeImpl;
import com.is.servidor_barrio.business.facade.base.PaisFacade;
import com.is.servidor_barrio.business.logic.service.BaseService;
import com.is.servidor_barrio.business.mapper.BaseMapper;

@Service
public class PaisFacadeImp extends BaseFacadeImpl<Pais, PaisDto, PaisCreateDto, PaisCreateDto, String>
    implements PaisFacade {

  public PaisFacadeImp(BaseService<Pais, String> baseService,
      BaseMapper<Pais, PaisDto, PaisCreateDto, PaisCreateDto> baseMapper) {
    super(baseService, baseMapper);

  }

  @Override
  public PaisDto save(PaisCreateDto request) throws Exception {
    System.out.println(request.getNombre());
    var entityToCreate = baseMapper.toEntityCreate(request);
    System.out.println(entityToCreate.getNombre());
    var entityCreated = baseService.save(entityToCreate);
    System.out.println(entityCreated.getNombre());
    return baseMapper.toDTO(entityCreated);
  }

}
