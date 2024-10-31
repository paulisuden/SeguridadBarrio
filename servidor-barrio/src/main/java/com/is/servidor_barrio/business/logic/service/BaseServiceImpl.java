package com.is.servidor_barrio.business.logic.service;

import com.is.servidor_barrio.business.domain.entity.Base;
import com.is.servidor_barrio.business.repository.BaseRepository;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<E extends Base, ID extends Serializable> implements BaseService<E, ID> {
    protected BaseRepository<E, ID> baseRepository;

    public BaseServiceImpl(BaseRepository<E, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    @Transactional
    public List<E> findAll() throws Exception {
        try {
            // Filtrar para devolver solo los registros que no están eliminados
            List<E> entities = baseRepository.findAllByEliminadoFalse();
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E findById(ID id) throws Exception {
        try {
            // Verificar si el registro está marcado como eliminado
            Optional<E> entityOptional = baseRepository.findByIdAndEliminadoFalse(id);
            return entityOptional.orElseThrow(() -> new Exception("Entity not found or marked as deleted"));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E save(E entity) throws Exception {
        try {
            entity = baseRepository.save(entity);
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E update(ID id, E entity) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findByIdAndEliminadoFalse(id);
            if (entityOptional.isPresent()) {
                E entityUpdate = entityOptional.get();
                entityUpdate = baseRepository.save(entity); // Actualizamos la entidad
                return entityUpdate;
            } else {
                throw new Exception("Entity not found or marked as deleted");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(ID id) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findByIdAndEliminadoFalse(id);
            if (entityOptional.isPresent()) {
                E entityToDelete = entityOptional.get();
                // Marcar como eliminado en lugar de borrarlo físicamente
                entityToDelete.setEliminado(true);
                baseRepository.save(entityToDelete);
                return true;
            } else {
                throw new Exception("Entity not found or already marked as deleted");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
