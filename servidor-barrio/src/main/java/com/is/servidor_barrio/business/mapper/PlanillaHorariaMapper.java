package com.is.servidor_barrio.business.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.is.servidor_barrio.business.domain.dto.departamento.DepartamentoCreateDto;
import com.is.servidor_barrio.business.domain.dto.departamento.DepartamentoDto;
import com.is.servidor_barrio.business.domain.dto.planillaHoraria.PlanillaHorariaCreateDto;
import com.is.servidor_barrio.business.domain.dto.planillaHoraria.PlanillaHorariaDto;
import com.is.servidor_barrio.business.domain.entity.Departamento;
import com.is.servidor_barrio.business.domain.entity.PlanillaHoraria;

@Mapper(componentModel = "spring")
public interface PlanillaHorariaMapper
    extends BaseMapper<PlanillaHoraria, PlanillaHorariaDto, PlanillaHorariaCreateDto, PlanillaHorariaCreateDto> {

    
    @Override
    @Mapping(target = "empleado", ignore = true)
    PlanillaHoraria toUpdate(@MappingTarget PlanillaHoraria entity, PlanillaHorariaCreateDto dto);

    @Override
    @Mapping(target = "empleadoId", ignore = true)
    PlanillaHorariaDto toDTO(PlanillaHoraria entity);
    
    }
