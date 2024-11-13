package com.is.servidor_barrio.business.facade.base;

import java.util.Optional;

import com.is.servidor_barrio.business.domain.dto.usuario.UsuarioCreateDTO;
import com.is.servidor_barrio.business.domain.dto.usuario.UsuarioDTO;
import com.is.servidor_barrio.business.facade.BaseFacade;
import com.is.servidor_barrio.business.logic.error.ErrorServiceException;

public interface UsuarioFacade extends BaseFacade<UsuarioDTO, UsuarioCreateDTO, UsuarioCreateDTO, Long> {
    UsuarioDTO searchByCuenta(String cuenta) throws Exception;
    UsuarioDTO searchByCuentaAndClave(String cuenta, String clave) throws Exception;
    UsuarioDTO crear(UsuarioCreateDTO usuario) throws Exception;
    UsuarioDTO searchByIdPersona(String idPersona) throws Exception;
    Optional<UsuarioDTO> buscarPorEmail(String email) throws ErrorServiceException;
}
