package com.is.servidor_barrio.business.mapper;

import org.mapstruct.Mapper;

import com.is.servidor_barrio.business.domain.dto.imagen.ImagenCreateDto;
import com.is.servidor_barrio.business.domain.dto.imagen.ImagenDto;
import com.is.servidor_barrio.business.domain.entity.Imagen;

@Mapper(componentModel = "spring")
public interface ImagenMapper extends BaseMapper<Imagen, ImagenDto, ImagenCreateDto, ImagenCreateDto> {

}