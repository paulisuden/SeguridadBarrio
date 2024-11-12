package com.is.servidor_barrio.business.mapper;

import org.mapstruct.Mapper;

import com.is.servidor_barrio.business.domain.dto.contacto.ContactoCreateDto;
import com.is.servidor_barrio.business.domain.dto.contacto.ContactoDto;
import com.is.servidor_barrio.business.domain.entity.ContactoTelefonico;

@Mapper(componentModel = "spring")
public interface ContactoTelefonoMapper
                extends BaseMapper<ContactoTelefonico, ContactoDto, ContactoCreateDto, ContactoCreateDto> {

}