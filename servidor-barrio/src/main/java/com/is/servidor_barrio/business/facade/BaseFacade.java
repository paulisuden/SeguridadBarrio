package com.is.servidor_barrio.business.facade;

import java.io.Serializable;
import java.util.List;

/*BaseFacade:
Esta interfaz define las operaciones básicas que se pueden realizar en cualquier entidad del sistema.
Incluye métodos como createNew, getById, getAll, deleteById y update.*/
public interface BaseFacade<D, DC, DE, ID extends Serializable> {
  public D save(DC request) throws Exception;

  public D findById(String id) throws Exception;

  public List<D> findAll() throws Exception;

  public boolean delete(String id) throws Exception;

  public D update(String id, DE request) throws Exception;
}
