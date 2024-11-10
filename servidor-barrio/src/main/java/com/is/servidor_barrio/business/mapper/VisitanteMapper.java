package com.is.servidor_barrio.business.mapper;

import org.mapstruct.Mapper;

import com.is.servidor_barrio.business.domain.dto.visitante.VisitanteCreateDto;
import com.is.servidor_barrio.business.domain.dto.visitante.VisitanteDto;
import com.is.servidor_barrio.business.domain.entity.Visitante;

@Mapper(componentModel = "spring")
public interface VisitanteMapper extends BaseMapper<Visitante, VisitanteDto, VisitanteCreateDto, VisitanteCreateDto> {
}
