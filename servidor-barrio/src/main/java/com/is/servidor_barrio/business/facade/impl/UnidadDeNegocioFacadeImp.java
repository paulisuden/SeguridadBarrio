package com.is.servidor_barrio.business.facade.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.dto.unidadDeNegocio.UnidadDeNegocioCreateDto;
import com.is.servidor_barrio.business.domain.dto.unidadDeNegocio.UnidadDeNegocioDto;
import com.is.servidor_barrio.business.domain.entity.Servicio;
import com.is.servidor_barrio.business.domain.entity.UnidadDeNegocio;
import com.is.servidor_barrio.business.facade.BaseFacadeImpl;
import com.is.servidor_barrio.business.logic.service.BaseService;
import com.is.servidor_barrio.business.logic.service.DireccionServiceImpl;
import com.is.servidor_barrio.business.logic.service.ServicioServiceImpl;
import com.is.servidor_barrio.business.mapper.BaseMapper;

@Service
public class UnidadDeNegocioFacadeImp extends
    BaseFacadeImpl<UnidadDeNegocio, UnidadDeNegocioDto, UnidadDeNegocioCreateDto, UnidadDeNegocioCreateDto, String> {

  @Autowired
  private DireccionServiceImpl direccionService;

  @Autowired
  private ServicioServiceImpl servicioService;

  public UnidadDeNegocioFacadeImp(BaseService<UnidadDeNegocio, String> baseService,
      BaseMapper<UnidadDeNegocio, UnidadDeNegocioDto, UnidadDeNegocioCreateDto, UnidadDeNegocioCreateDto> baseMapper) {
    super(baseService, baseMapper);
  }

  public UnidadDeNegocioDto save(UnidadDeNegocioCreateDto unidadDeNegocioCreateDto) throws Exception {
    var negocioEntity = baseMapper.toEntityCreate(unidadDeNegocioCreateDto);
    var direccionEntity = direccionService.findById(unidadDeNegocioCreateDto.getDireccionId());
    negocioEntity.setDireccion(direccionEntity);

    // Obtener la lista de servicios evitando los servicios nulos en caso de
    // excepción
    List<Servicio> servicios = unidadDeNegocioCreateDto.getServiciosId().stream()
        .map(id -> {
          try {
            return servicioService.findById(id);
          } catch (Exception e) {
            return null;
          }
        })
        .filter(Objects::nonNull) // Filtrar los nulos
        .collect(Collectors.toList());
    negocioEntity.setServicios(servicios);
    var entityCreated = baseService.save(negocioEntity);
    return baseMapper.toDTO(entityCreated);
  }

  @Override
  public UnidadDeNegocioDto update(String id, UnidadDeNegocioCreateDto unidadDeNegocioCreateDto) throws Exception {
    var negocioEntity = baseService.findById(id);
    baseMapper.toUpdate(negocioEntity, unidadDeNegocioCreateDto);

    // Update Direccion if it has changed
    if (!negocioEntity.getDireccion().getId().equals(unidadDeNegocioCreateDto.getDireccionId())) {
      var direccionEntity = direccionService.findById(unidadDeNegocioCreateDto.getDireccionId());
      negocioEntity.setDireccion(direccionEntity);
    }

    // Update Servicios list based on serviciosId in the DTO
    List<Servicio> servicios = unidadDeNegocioCreateDto.getServiciosId().stream()
        .map(idService -> {
          try {
            return servicioService.findById(idService);
          } catch (Exception e) {
            e.printStackTrace(); // Log the error or handle it as needed
          }
          return null;
        })
        .filter(Objects::nonNull) // Remove nulls from the list
        .collect(Collectors.toList());
    negocioEntity.setServicios(servicios);

    var updatedEntity = baseService.update(id, negocioEntity);
    return baseMapper.toDTO(updatedEntity);
  }

}