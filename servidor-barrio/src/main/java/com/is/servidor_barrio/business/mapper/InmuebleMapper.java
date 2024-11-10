package com.is.servidor_barrio.business.mapper;

import org.mapstruct.Mapper;

import com.is.servidor_barrio.business.domain.dto.inmueble.InmuebleCreateDto;
import com.is.servidor_barrio.business.domain.dto.inmueble.InmuebleDto;
import com.is.servidor_barrio.business.domain.entity.Inmueble;

@Mapper(componentModel = "spring")
public interface InmuebleMapper extends BaseMapper<Inmueble, InmuebleDto, InmuebleCreateDto, InmuebleCreateDto> {
}
