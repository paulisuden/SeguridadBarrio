package com.is.servidor_barrio.business.repository;

import org.springframework.stereotype.Repository;

import com.is.servidor_barrio.business.domain.entity.Contacto;

@Repository
public interface ContactoRepository extends BaseRepository<Contacto, String> {
}
