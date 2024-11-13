package com.is.servidor_barrio.business.facade.impl;

import com.is.servidor_barrio.business.domain.dto.usuario.UsuarioCreateDTO;
import com.is.servidor_barrio.business.domain.dto.usuario.UsuarioDTO;
import com.is.servidor_barrio.business.domain.entity.Usuario;
import com.is.servidor_barrio.business.facade.BaseFacadeImpl;
import com.is.servidor_barrio.business.logic.service.BaseService;
import com.is.servidor_barrio.business.logic.service.UsuarioService;
import com.is.servidor_barrio.business.mapper.BaseMapper;
import com.is.servidor_barrio.business.mapper.UsuarioMapper;

public class UsuarioFacadeImpl extends BaseFacadeImpl<Usuario, UsuarioDTO, UsuarioCreateDTO, UsuarioCreateDTO, Long> {

    public UsuarioFacadeImpl(BaseService<Usuario, Long> baseService,
        BaseMapper<Usuario, UsuarioDTO, UsuarioCreateDTO, UsuarioCreateDTO> baseMapper) {
    super(baseService, baseMapper);
    }

    protected UsuarioService baseService;
    protected UsuarioMapper baseMapper;

    UsuarioDTO searchByCuenta(String cuenta) throws Exception;
    
}
