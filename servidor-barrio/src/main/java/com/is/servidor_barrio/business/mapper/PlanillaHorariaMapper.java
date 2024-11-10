package com.is.servidor_barrio.business.mapper;

import org.mapstruct.Mapper;

import com.is.servidor_barrio.business.domain.dto.planillaHoraria.PlanillaHorariaCreateDto;
import com.is.servidor_barrio.business.domain.dto.planillaHoraria.PlanillaHorariaDto;
import com.is.servidor_barrio.business.domain.entity.PlanillaHoraria;

@Mapper(componentModel = "spring")
public interface PlanillaHorariaMapper
    extends BaseMapper<PlanillaHoraria, PlanillaHorariaDto, PlanillaHorariaCreateDto, PlanillaHorariaCreateDto> {
}
