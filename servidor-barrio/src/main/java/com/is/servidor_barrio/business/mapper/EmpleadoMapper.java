package com.is.servidor_barrio.business.mapper;

import org.mapstruct.Mapper;

import com.is.servidor_barrio.business.domain.dto.persona.PersonaCreateDto;
import com.is.servidor_barrio.business.domain.dto.persona.PersonaDto;
import com.is.servidor_barrio.business.domain.entity.Empleado;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper extends BaseMapper<Empleado, PersonaDto, PersonaCreateDto, PersonaCreateDto> {

}
