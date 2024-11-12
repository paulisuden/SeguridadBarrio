package com.is.servidor_barrio.business.mapper;

import org.mapstruct.Mapper;

import com.is.servidor_barrio.business.domain.dto.unidadDeNegocio.UnidadDeNegocioCreateDto;
import com.is.servidor_barrio.business.domain.dto.unidadDeNegocio.UnidadDeNegocioDto;
import com.is.servidor_barrio.business.domain.entity.UnidadDeNegocio;

@Mapper(componentModel = "spring")
public interface UnidadDeNegocioMapper
    extends BaseMapper<UnidadDeNegocio, UnidadDeNegocioDto, UnidadDeNegocioCreateDto, UnidadDeNegocioCreateDto> {

}
