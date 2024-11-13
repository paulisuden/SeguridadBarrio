package com.is.servidor_barrio.business.facade.base;

import com.is.servidor_barrio.business.domain.dto.departamento.DepartamentoCreateDto;
import com.is.servidor_barrio.business.domain.dto.departamento.DepartamentoDto;
import com.is.servidor_barrio.business.facade.BaseFacade;

public interface DepartamentoFacade
        extends BaseFacade<DepartamentoDto, DepartamentoCreateDto, DepartamentoCreateDto, String> {

}
