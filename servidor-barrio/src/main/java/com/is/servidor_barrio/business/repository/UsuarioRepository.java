package com.is.servidor_barrio.business.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.is.servidor_barrio.business.domain.entity.Usuario;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {
    Usuario findByEmailAndClaveAndEliminadoFalse(String email, String clave);
    Usuario findByEmail(String email);
    @Query("SELECT u FROM Usuario u WHERE u.id = (SELECT p.usuario.id FROM Persona p WHERE p.id = :personaId)")
    Usuario findByPersonaId(@Param("personaId") String personaId);
    Usuario findByEmailAndEliminadoFalse(String email);

}
