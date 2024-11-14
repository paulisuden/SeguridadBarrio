package com.is.servidor_barrio.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.is.servidor_barrio.business.domain.entity.MovimientoVisita;

@Repository
public interface MovimientoVisitaRepository extends BaseRepository<MovimientoVisita, String> {
    @Query("SELECT u FROM MovimientoVisita u WHERE u.inmueble.id = :inmuebleId")
    public List<MovimientoVisita> listarPorInmuebleId(@Param("inmuebleId") String inmuebleId);


}
//(SELECT p.inmueble.id FROM MovimientoVisita p WHERE p.inmueble.id = :inmuebleId ORDER BY p.fecha ASC LIMIT 1)