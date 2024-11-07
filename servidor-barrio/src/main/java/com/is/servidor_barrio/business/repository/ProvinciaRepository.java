package com.is.servidor_barrio.business.repository;

import com.is.servidor_barrio.business.domain.entity.Provincia;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProvinciaRepository extends BaseRepository<Provincia, Long> {

    @Query("SELECT p FROM Provincia p WHERE p.nombre = :nombre AND p.pais.id = :idPais")
    Optional<Provincia> findProvinciaByNombreAndPaisId(String nombre, Long idPais);
}

