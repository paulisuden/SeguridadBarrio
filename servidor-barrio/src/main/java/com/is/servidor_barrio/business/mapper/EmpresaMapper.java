package com.is.servidor_barrio.business.mapper;

import org.mapstruct.Mapper;

import com.is.servidor_barrio.business.domain.dto.empresa.EmpresaCreateDto;
import com.is.servidor_barrio.business.domain.dto.empresa.EmpresaDto;
import com.is.servidor_barrio.business.domain.entity.Empresa;

@Mapper(componentModel = "spring")
public interface EmpresaMapper extends BaseMapper<Empresa, EmpresaDto, EmpresaCreateDto, EmpresaCreateDto> {
}