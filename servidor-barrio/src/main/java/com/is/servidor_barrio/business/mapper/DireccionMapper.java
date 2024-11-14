package com.is.servidor_barrio.business.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.is.servidor_barrio.business.domain.dto.direccion.DireccionCreateDto;
import com.is.servidor_barrio.business.domain.dto.direccion.DireccionDto;
import com.is.servidor_barrio.business.domain.entity.Direccion;

@Mapper(componentModel = "spring")
public interface DireccionMapper extends BaseMapper<Direccion, DireccionDto, DireccionCreateDto, DireccionCreateDto> {
  // @Override
  // @Mapping(target = "localidad", ignore = true)
  // Direccion toUpdate(@MappingTarget Direccion entity, DireccionCreateDto dto);

  // @Override
  // @Mapping(target = "localidad", ignore = true)
  // Direccion toEntityCreate(DireccionCreateDto dto);

  // @Override
  // @Mapping(target = "localidadId", ignore = true)
  // DireccionDto toDTO(Direccion entity);
}