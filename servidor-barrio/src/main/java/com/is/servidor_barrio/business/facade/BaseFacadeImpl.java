package com.is.servidor_barrio.business.facade;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.is.servidor_barrio.business.domain.dto.BaseDto;
import com.is.servidor_barrio.business.domain.entity.Base;
import com.is.servidor_barrio.business.logic.service.BaseService;
import com.is.servidor_barrio.business.mapper.BaseMapper;

/*BaseFacadeImp:
Esta es la implementación concreta del BaseFacade.
Proporciona la lógica para realizar las operaciones básicas definidas en la interfaz BaseFacade.
En su constructor, recibe instancias de un servicio base (BaseService) y un mapper base (BaseMapper),
que se utilizan para interactuar con la capa de persistencia y convertir entre entidades y DTOs.*/
public abstract class BaseFacadeImpl<E extends Base, D extends BaseDto, DC, DE, ID extends Serializable>
    implements BaseFacade<D, DC, DE, ID> {

  protected BaseService<E, ID> baseService;
  protected BaseMapper<E, D, DC, DE> baseMapper;

  /*
   * Constructor:
   * Recibe dos parámetros: un baseService del tipo BaseService y un baseMapper
   * del tipo BaseMapper.
   * Estos son necesarios para realizar operaciones de CRUD (Crear, Leer,
   * Actualizar, Eliminar)
   * sobre las entidades y mapearlas a DTOs.
   */
  public BaseFacadeImpl(BaseService<E, ID> baseService, BaseMapper<E, D, DC, DE> baseMapper) {
    this.baseService = baseService;
    this.baseMapper = baseMapper;
  }

  /*
   * createNew(createDto):
   * Crea una nueva entidad a partir de un DTO de creación (DC),
   * la guarda utilizando el servicio base y devuelve el DTO resultante.
   */
  public D save(DC request) throws Exception {
    var entityToCreate = baseMapper.toEntityCreate(request);
    var entityCreated = baseService.save(entityToCreate);
    return baseMapper.toDTO(entityCreated);
  }

  /*
   * getById(id): Obtiene una entidad por su ID utilizando el servicio base y la
   * convierte en un DTO.
   */
  public D findById(ID id) throws Exception {
    var entity = baseService.findById(id);

    return baseMapper.toDTO(entity);
  }

  /*
   * getAll():
   * Obtiene todas las entidades utilizando el servicio base,
   * las convierte en DTOs y las devuelve en forma de lista.
   */
  public List<D> findAll() throws Exception {
    var entities = baseService.findAll();
    return entities
        .stream()
        .map(baseMapper::toDTO)
        .collect(Collectors.toList());
  }

  /* deleteById(id): Elimina una entidad por su ID utilizando el servicio base. */
  public boolean delete(ID id) throws Exception {
    return baseService.delete(id);
  }

  /*
   * update(body,id):
   * Actualiza una entidad existente utilizando un DTO de actualización (DE).
   * Primero obtiene la entidad existente por su ID,
   * la actualiza con los datos del DTO utilizando el mapper
   * y luego la actualiza en la base de datos utilizando el servicio base.
   * Finalmente, devuelve el DTO actualizado.
   */
  public D update(ID id, DE request) throws Exception {
    var entityToUpdate = baseService.findById(id);
    var entityUpdatedByMapper = baseMapper.toUpdate(entityToUpdate, request);
    var entityUpdatedByService = baseService.update(id, entityUpdatedByMapper);
    return baseMapper.toDTO(entityUpdatedByService);
  }

}
