package com.is.servidor_barrio.business.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.is.servidor_barrio.business.domain.entity.Persona;

@Repository
public interface PersonaRepository extends BaseRepository<Persona, String> {
    @Query("SELECT u FROM Persona u WHERE u.usuario.id = (SELECT p.usuario.id FROM Persona p WHERE p.usuario.id = :usuarioId)")
    public Persona findByUsuarioId(@Param("usuarioId") String usuarioId);

}
