package com.is.servidor_barrio.business.mapper;

import org.mapstruct.Mapper;
import com.is.servidor_barrio.business.domain.dto.inmueble.InmuebleCreateDto;
import com.is.servidor_barrio.business.domain.dto.inmueble.InmuebleDto;
import com.is.servidor_barrio.business.domain.entity.Inmueble;

@Mapper(componentModel = "spring")
public interface InmuebleMapper extends BaseMapper<Inmueble, InmuebleDto, InmuebleCreateDto, InmuebleCreateDto> {

    /*
     * @Override
     * //@Mapping(target = "unidadDeNegocio", ignore = true)
     * MovimientoVisita toUpdate(@MappingTarget MovimientoVisita entity,
     * MovimientoVisitaCreateDTO dto);
     * 
     * @Override
     * //@Mapping(target = "idUnidadDeNegocio", ignore = true)
     * MovimientoVisitaDTO toDTO(MovimientoVisita entity);
     */
}
