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
public class PaisFacadeImp extends BaseFacadeImpl<Pais, PaisDto, PaisCreateDto, PaisCreateDto, Long>
    implements PaisFacade {
  public PaisFacadeImp(BaseService<Pais, Long> baseService,
      BaseMapper<Pais, PaisDto, PaisCreateDto, PaisCreateDto> baseMapper) {
    super(baseService, baseMapper);
  }

}
