package com.is.servidor_barrio.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.is.servidor_barrio.business.domain.entity.Inmueble;

@Repository
public interface InmuebleRespository extends BaseRepository<Inmueble, String> {

}
