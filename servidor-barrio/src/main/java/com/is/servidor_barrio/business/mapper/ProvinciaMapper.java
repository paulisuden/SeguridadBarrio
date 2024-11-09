package com.is.servidor_barrio.business.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.is.servidor_barrio.business.domain.dto.provincia.ProvinciaCreateDto;
import com.is.servidor_barrio.business.domain.dto.provincia.ProvinciaDto;
import com.is.servidor_barrio.business.domain.entity.Provincia;

@Mapper(componentModel = "spring")
public interface ProvinciaMapper extends BaseMapper<Provincia, ProvinciaDto, ProvinciaCreateDto, ProvinciaCreateDto> {
  @Override
  @Mapping(target = "pais", ignore = true)
  Provincia toUpdate(@MappingTarget Provincia entity, ProvinciaCreateDto dto);
}