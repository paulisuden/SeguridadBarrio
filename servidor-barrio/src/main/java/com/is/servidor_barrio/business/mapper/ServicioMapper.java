package com.is.servidor_barrio.business.mapper;

import org.mapstruct.Mapper;

import com.is.servidor_barrio.business.domain.dto.servicio.ServicioCreateDto;
import com.is.servidor_barrio.business.domain.dto.servicio.ServicioDto;
import com.is.servidor_barrio.business.domain.entity.Servicio;

@Mapper(componentModel = "spring")
public interface ServicioMapper extends BaseMapper<Servicio, ServicioDto, ServicioCreateDto, ServicioCreateDto> {
}
