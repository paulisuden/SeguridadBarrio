package com.is.servidor_barrio.business.facade.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.dto.persona.PersonaCreateDto;
import com.is.servidor_barrio.business.domain.dto.persona.PersonaDto;
import com.is.servidor_barrio.business.domain.entity.Empleado;
import com.is.servidor_barrio.business.domain.entity.Habitante;
import com.is.servidor_barrio.business.domain.entity.Inmueble;
import com.is.servidor_barrio.business.domain.entity.Persona;
import com.is.servidor_barrio.business.domain.entity.UnidadDeNegocio;
import com.is.servidor_barrio.business.facade.BaseFacadeImpl;
import com.is.servidor_barrio.business.logic.service.BaseService;
import com.is.servidor_barrio.business.logic.service.InmuebleServiceImpl;
import com.is.servidor_barrio.business.logic.service.UnidadDeNegocioServiceImpl;
import com.is.servidor_barrio.business.mapper.BaseMapper;
import com.is.servidor_barrio.business.mapper.EmpleadoMapper;
import com.is.servidor_barrio.business.mapper.HabitanteMapper;
import com.is.servidor_barrio.business.mapper.PersonaMapper;

@Service
public class PersonaFacadeImpl extends BaseFacadeImpl<Persona, PersonaDto, PersonaCreateDto, PersonaCreateDto, Long> {

  @Autowired
  private InmuebleServiceImpl inmuebleService;

  @Autowired
  private UnidadDeNegocioServiceImpl negocioService;

  @Autowired
  private EmpleadoMapper empleadoMapper;

  @Autowired
  private HabitanteMapper habitanteMapper;

  @Autowired
  private PersonaMapper personaMapper;

  public PersonaFacadeImpl(BaseService<Persona, Long> baseService,
      BaseMapper<Persona, PersonaDto, PersonaCreateDto, PersonaCreateDto> baseMapper) {
    super(baseService, baseMapper);
  }

  public PersonaDto save(PersonaCreateDto personaCreateDto) throws Exception {
    // Crear la entidad Persona (Empleado o Habitante) utilizando el mapper
    Persona personaEntity;

    if (personaCreateDto.getLegajo() == null || personaCreateDto.getLegajo().isEmpty()) {
      // Crear Habitante usando el mapper de Habitante
      Habitante habitante = habitanteMapper.toEntityCreate(personaCreateDto);

      // Asignar inmueble si existe
      if (personaCreateDto.getInmuebleId() != null) {
        Inmueble inmueble = inmuebleService.findById(personaCreateDto.getInmuebleId());
        habitante.setInmueble(inmueble);
      }

      personaEntity = habitante;
    } else {
      // Crear Empleado usando el mapper de Empleado
      Empleado empleadoEntity = empleadoMapper.toEntityCreate(personaCreateDto);

      // Asignar negocios (si los hay)
      if (personaCreateDto.getNegociosId() != null && personaCreateDto.getNegociosId().length > 0) {
        List<UnidadDeNegocio> negocios = Arrays.stream(personaCreateDto.getNegociosId())
            .map(idNegocio -> {
              try {
                return negocioService.findById(idNegocio);
              } catch (Exception e) {
                return null;
              }
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        empleadoEntity.setNegocios(negocios);
      }

      personaEntity = empleadoEntity;
    }

    // Guardar la entidad Persona (Empleado o Habitante)
    var entityCreated = baseService.save(personaEntity);
    return baseMapper.toDTO(entityCreated); // Convertir la entidad a DTO
  }

  @Override
  public PersonaDto update(Long id, PersonaCreateDto personaCreateDto) throws Exception {
    // Buscar la entidad existente
    Persona personaEntity = baseService.findById(id);

    // Actualizar la entidad usando el mapper
    baseMapper.toUpdate(personaEntity, personaCreateDto);

    // Si es un Habitante, asignar los datos específicos
    if (personaEntity instanceof Habitante) {
      Habitante habitante = (Habitante) personaEntity;
      if (personaCreateDto.getInmuebleId() != null) {
        Inmueble inmueble = inmuebleService.findById(personaCreateDto.getInmuebleId());
        habitante.setInmueble(inmueble);
      }
    }

    // Si es un Empleado, asignar los datos específicos
    if (personaEntity instanceof Empleado) {
      Empleado empleado = (Empleado) personaEntity;
      // Asignar negocios
      if (personaCreateDto.getNegociosId() != null && personaCreateDto.getNegociosId().length > 0) {
        List<UnidadDeNegocio> negocios = Arrays.stream(personaCreateDto.getNegociosId())
            .map(idNegocio -> {
              try {
                return negocioService.findById(idNegocio);
              } catch (Exception e) {
                return null;
              }
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        empleado.setNegocios(negocios);
      }
    }

    // Guardar la entidad actualizada
    var updatedEntity = baseService.update(id, personaEntity);
    return baseMapper.toDTO(updatedEntity); // Convertir la entidad actualizada a DTO
  }
}
