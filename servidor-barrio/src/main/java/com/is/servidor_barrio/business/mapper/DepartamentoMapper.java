package com.is.servidor_barrio.business.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.is.servidor_barrio.business.domain.dto.departamento.DepartamentoCreateDto;
import com.is.servidor_barrio.business.domain.dto.departamento.DepartamentoDto;
import com.is.servidor_barrio.business.domain.entity.Departamento;

@Mapper(componentModel = "spring")
public interface DepartamentoMapper
    extends BaseMapper<Departamento, DepartamentoDto, DepartamentoCreateDto, DepartamentoCreateDto> {
  @Override
  @Mapping(target = "provincia", ignore = true)
  Departamento toUpdate(@MappingTarget Departamento entity, DepartamentoCreateDto dto);
}