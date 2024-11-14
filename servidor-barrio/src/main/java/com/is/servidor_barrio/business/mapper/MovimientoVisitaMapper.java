package com.is.servidor_barrio.business.mapper;

import com.is.servidor_barrio.business.domain.dto.movimientoVisita.MovimientoVisitaCreateDTO;
import com.is.servidor_barrio.business.domain.dto.movimientoVisita.MovimientoVisitaDTO;
import com.is.servidor_barrio.business.domain.entity.MovimientoVisita;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MovimientoVisitaMapper extends
        BaseMapper<MovimientoVisita, MovimientoVisitaDTO, MovimientoVisitaCreateDTO, MovimientoVisitaCreateDTO> {

    // @Override
    // @Mapping(target = "visitante", ignore = true)
    // @Mapping(target = "inmueble", ignore = true)
    // MovimientoVisita toUpdate(@MappingTarget MovimientoVisita entity,
    // MovimientoVisitaCreateDTO dto);

    // @Override
    // @Mapping(target = "idVisitante", ignore = true)
    // @Mapping(target = "idInmuebles", ignore = true)
    // MovimientoVisitaDTO toDTO(MovimientoVisita entity);
}
