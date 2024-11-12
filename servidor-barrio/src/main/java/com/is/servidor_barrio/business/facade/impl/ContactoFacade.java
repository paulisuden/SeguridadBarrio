package com.is.servidor_barrio.business.facade.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.dto.contacto.ContactoCreateDto;
import com.is.servidor_barrio.business.domain.dto.contacto.ContactoDto;
import com.is.servidor_barrio.business.domain.entity.Contacto;
import com.is.servidor_barrio.business.domain.entity.ContactoEmail;
import com.is.servidor_barrio.business.domain.entity.ContactoTelefonico;
import com.is.servidor_barrio.business.facade.BaseFacadeImpl;
import com.is.servidor_barrio.business.logic.service.BaseService;
import com.is.servidor_barrio.business.mapper.BaseMapper;
import com.is.servidor_barrio.business.mapper.ContactoEmailMapperImpl;
import com.is.servidor_barrio.business.mapper.ContactoTelefonoMapperImpl;

@Service
public class ContactoFacade
    extends BaseFacadeImpl<Contacto, ContactoDto, ContactoCreateDto, ContactoCreateDto, Long> {

  // Usa los mappers de cada subclase de contacto
  private BaseMapper<ContactoEmail, ContactoDto, ContactoCreateDto, ContactoCreateDto> emailMapper;
  private BaseMapper<ContactoTelefonico, ContactoDto, ContactoCreateDto, ContactoCreateDto> telefonoMapper;

  public ContactoFacade(BaseService<Contacto, Long> baseService,
      BaseMapper<Contacto, ContactoDto, ContactoCreateDto, ContactoCreateDto> baseMapper) {
    super(baseService, baseMapper);
    emailMapper = new ContactoEmailMapperImpl();
    telefonoMapper = new ContactoTelefonoMapperImpl();
  }

  @Override
  public ContactoDto save(ContactoCreateDto createDto) throws Exception {

    if (createDto.getEmail() != null && !createDto.getEmail().isEmpty()) {
      ContactoEmail contacto = emailMapper.toEntityCreate(createDto);
      baseService.save(contacto);
      return emailMapper.toDTO(contacto);
    } else {
      ContactoTelefonico contacto = telefonoMapper.toEntityCreate(createDto);
      baseService.save(contacto);
      return telefonoMapper.toDTO(contacto);
    }
  }

  @Override
  public ContactoDto update(Long id, ContactoCreateDto createDto) throws Exception {

    // Busca el contacto
    Contacto contacto = baseService.findById(id);

    // Actualiza el contenido con el mapper respectivo
    if (contacto instanceof ContactoEmail) {
      emailMapper.toUpdate((ContactoEmail) contacto, createDto);
      contacto = baseService.update(id, contacto);
      return emailMapper.toDTO((ContactoEmail) contacto);

    } else {
      telefonoMapper.toUpdate((ContactoTelefonico) contacto, createDto);
      contacto = baseService.update(id, contacto);
      return telefonoMapper.toDTO((ContactoTelefonico) contacto);
    }
  }

  @Override
  public ContactoDto findById(Long id) throws Exception {
    var entity = baseService.findById(id);
    if (entity instanceof ContactoEmail)
      return emailMapper.toDTO((ContactoEmail) entity);

    return telefonoMapper.toDTO((ContactoTelefonico) entity);
  }

  @Override
  public List<ContactoDto> findAll() throws Exception {
    var entities = baseService.findAll();
    var dtos = new ArrayList<ContactoDto>();

    // Mapea las entidades a DTO usando el mapper correspondiente segun el tipo
    for (Contacto contacto : entities) {

      if (contacto instanceof ContactoEmail)
        dtos.add(emailMapper.toDTO((ContactoEmail) contacto));

      else
        dtos.add(telefonoMapper.toDTO((ContactoTelefonico) contacto));
    }
    return dtos;
  }

}
