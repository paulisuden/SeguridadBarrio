package com.is.servidor_barrio.business.logic.service;

import java.util.Optional;

import com.is.servidor_barrio.business.domain.entity.Usuario;
import com.is.servidor_barrio.business.logic.error.ErrorServiceException;

public interface UsuarioService extends BaseService<Usuario, Long> {
    Usuario searchByCuenta(String cuenta) throws Exception;
    Usuario searchByCuentaAndClave(String cuenta, String clave) throws Exception;
    Usuario crear(Usuario usuario) throws Exception;
    Usuario searchByIdPersona(String idPersona) throws Exception;
    Optional<Usuario> buscarPorEmail(String email) throws ErrorServiceException;
}
