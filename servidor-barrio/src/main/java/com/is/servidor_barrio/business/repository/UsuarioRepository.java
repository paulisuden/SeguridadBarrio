package com.is.servidor_barrio.business.repository;

import org.springframework.stereotype.Repository;

import com.is.servidor_barrio.business.domain.entity.Usuario;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {

}
