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
import com.is.servidor_barrio.business.mapper.EmpleadoMapperImpl;
import com.is.servidor_barrio.business.mapper.HabitanteMapperImpl;

@Service
public class PersonaFacadeImpl extends BaseFacadeImpl<Persona, PersonaDto, PersonaCreateDto, PersonaCreateDto, String> {

  @Autowired
  private InmuebleServiceImpl inmuebleService;

  @Autowired
  private UnidadDeNegocioServiceImpl negocioService;

  private BaseMapper<Empleado, PersonaDto, PersonaCreateDto, PersonaCreateDto> empleadoMapper;
  private BaseMapper<Habitante, PersonaDto, PersonaCreateDto, PersonaCreateDto> habitanteMapper;

  public PersonaFacadeImpl(BaseService<Persona, String> baseService,
      BaseMapper<Persona, PersonaDto, PersonaCreateDto, PersonaCreateDto> baseMapper) {
    super(baseService, baseMapper);
    empleadoMapper = new EmpleadoMapperImpl();
    habitanteMapper = new HabitanteMapperImpl();
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
  public PersonaDto update(String id, PersonaCreateDto personaCreateDto) throws Exception {
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

  @Override
  public PersonaDto findById(String id) throws Exception {
    // Llama al servicio base para obtener la entidad Persona
    Persona personaEntity = baseService.findById(id);

    // Determina el tipo de Persona y convierte la entidad al DTO correspondiente
    if (personaEntity instanceof Empleado) {
      return empleadoMapper.toDTO((Empleado) personaEntity);
    } else if (personaEntity instanceof Habitante) {
      return habitanteMapper.toDTO((Habitante) personaEntity);
    }

    // Si no es ni Empleado ni Habitante, lanza una excepción
    throw new Exception("Tipo de persona no reconocido");
  }

  @Override
  public List<PersonaDto> findAll() throws Exception {
    // Llama al servicio base para obtener todas las entidades Persona
    List<Persona> personas = baseService.findAll();

    // Convierte cada entidad Persona a su respectivo DTO
    return personas.stream()
        .map(persona -> {
          if (persona instanceof Empleado) {
            return empleadoMapper.toDTO((Empleado) persona);
          } else if (persona instanceof Habitante) {
            return habitanteMapper.toDTO((Habitante) persona);
          } else {
            return null; // En caso de un tipo no reconocido
          }
        })
        .filter(Objects::nonNull) // Filtra posibles nulos si existen tipos no reconocidos
        .collect(Collectors.toList());
  }

}
