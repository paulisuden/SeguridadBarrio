package com.is.servidor_barrio.business.mapper;

import org.mapstruct.Mapper;

import com.is.servidor_barrio.business.domain.dto.persona.PersonaCreateDto;
import com.is.servidor_barrio.business.domain.dto.persona.PersonaDto;
import com.is.servidor_barrio.business.domain.entity.Persona;

@Mapper(componentModel = "spring")
public interface PersonaMapper extends BaseMapper<Persona, PersonaDto, PersonaCreateDto, PersonaCreateDto> {
}
