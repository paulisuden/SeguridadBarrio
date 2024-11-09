package com.is.servidor_barrio.business.mapper;

import java.util.List;

import org.mapstruct.MappingTarget;

import com.is.servidor_barrio.business.domain.dto.BaseDto;
import com.is.servidor_barrio.business.domain.entity.Base;

public interface BaseMapper<E extends Base, D extends BaseDto, DC, DE> {
  // toDTO(E source): Convierte una entidad en un DTO.
  public D toDTO(E source);

  // toEntity(D source): Convierte un DTO en una entidad.
  public E toEntity(D source);

  // toEntityCreate(DC source): Convierte un DTO de creación(createDto) en una
  // entidad.
  public E toEntityCreate(DC source);

  // toUpdate(@MappingTarget E entity, DE source): Actualiza una entidad con datos
  // de un DTO.
  // @MappingTarget se utiliza para reemplazar los atributos del dto sobre la
  // entidad
  public E toUpdate(@MappingTarget E entity, DE source);

  // toDTOsList(List<E> source): Convierte una lista de entidades en una lista de
  // DTOs.
  public List<D> toDTOsList(List<E> source);
}
