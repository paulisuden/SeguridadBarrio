package com.is.servidor_barrio.business.repository;

import org.springframework.stereotype.Repository;

import com.is.servidor_barrio.business.domain.entity.Empleado;

@Repository
public interface EmpleadoRepository extends BaseRepository<Empleado, String> {

}
