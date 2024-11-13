package com.is.servidor_barrio.business.logic.service;

import java.io.Serializable;
import java.util.List;

//Es de tipo generica
public interface BaseService<E, ID extends Serializable> {
    public List<E> findAll() throws Exception;

    public E findById(ID id) throws Exception;

    public E save(E entity) throws Exception;

    public E update(ID id, E entity) throws Exception;

    public boolean delete(ID id) throws Exception;
}