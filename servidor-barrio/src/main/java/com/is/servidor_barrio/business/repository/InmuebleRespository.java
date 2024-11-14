package com.is.servidor_barrio.business.repository;

import org.springframework.stereotype.Repository;

import com.is.servidor_barrio.business.domain.entity.Inmueble;

@Repository
public interface InmuebleRespository extends BaseRepository<Inmueble, String> {

}
