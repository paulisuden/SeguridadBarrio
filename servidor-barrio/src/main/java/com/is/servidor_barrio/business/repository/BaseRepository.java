package com.is.servidor_barrio.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<E, ID extends Serializable> extends JpaRepository<E, ID> {
  List<E> findAllByEliminadoFalse(); // Para obtener solo registros no eliminados
  Optional<E> findByIdAndEliminadoFalse(ID id); // Para obtener solo registros no eliminados por ID
}
