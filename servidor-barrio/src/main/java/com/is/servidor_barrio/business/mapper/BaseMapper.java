package com.is.servidor_barrio.business.mapper;

import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.IterableMapping;

import com.is.servidor_barrio.business.domain.dto.BaseDto;
import com.is.servidor_barrio.business.domain.entity.Base;

public interface BaseMapper<E extends Base, D extends BaseDto, DC, DE> {

  // Convierte una entidad en un DTO, omitiendo campos nulos y propiedades no
  // mapeadas
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, ignoreByDefault = true)
  public D toDTO(E source);

  // Convierte un DTO en una entidad, omitiendo campos nulos y propiedades no
  // mapeadas
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, ignoreByDefault = true)
  public E toEntity(D source);

  // Convierte un DTO de creación en una entidad, omitiendo campos nulos y
  // propiedades no mapeadas
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, ignoreByDefault = true)
  public E toEntityCreate(DC source);

  // Actualiza una entidad con datos de un DTO, omitiendo los valores nulos y
  // propiedades no mapeadas
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, ignoreByDefault = true)
  public E toUpdate(@MappingTarget E entity, DE source);

  // Convierte una lista de entidades en una lista de DTOs, manejando nulos en la
  // lista
  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  public List<D> toDTOsList(List<E> source);
}
