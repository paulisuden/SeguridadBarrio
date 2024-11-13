package com.is.servidor_barrio.business.domain.dto.usuario;

import com.is.servidor_barrio.business.domain.dto.BaseDto;
import com.is.servidor_barrio.business.domain.enumeration.Rol;

public class UsuarioDTO extends BaseDto{
    private String email;
    private String clave;
    private Rol rol;
    
}
