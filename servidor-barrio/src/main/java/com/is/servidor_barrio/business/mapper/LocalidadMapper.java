package com.is.servidor_barrio.business.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.is.servidor_barrio.business.domain.dto.localidad.LocalidadCreateDto;
import com.is.servidor_barrio.business.domain.dto.localidad.LocalidadDto;
import com.is.servidor_barrio.business.domain.entity.Localidad;

@Mapper(componentModel = "spring")
public interface LocalidadMapper extends BaseMapper<Localidad, LocalidadDto, LocalidadCreateDto, LocalidadCreateDto> {
  @Override
  @Mapping(target = "departamento", ignore = true)
  Localidad toUpdate(@MappingTarget Localidad entity, LocalidadCreateDto dto);
}