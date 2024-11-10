package com.is.servidor_barrio.business.mapper;

import org.mapstruct.Mapper;

import com.is.servidor_barrio.business.domain.dto.pais.PaisCreateDto;
import com.is.servidor_barrio.business.domain.dto.pais.PaisDto;
import com.is.servidor_barrio.business.domain.entity.Pais;

@Mapper(componentModel = "spring")
public interface PaisMapper extends BaseMapper<Pais, PaisDto, PaisCreateDto, PaisCreateDto> {
}
