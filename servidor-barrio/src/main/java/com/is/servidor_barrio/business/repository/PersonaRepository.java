package com.is.servidor_barrio.business.repository;

import org.springframework.stereotype.Repository;

import com.is.servidor_barrio.business.domain.entity.Persona;

@Repository
public interface PersonaRepository extends BaseRepository<Persona, String> {

}
