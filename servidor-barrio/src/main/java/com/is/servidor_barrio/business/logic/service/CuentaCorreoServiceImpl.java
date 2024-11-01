
package com.is.servidor_barrio.business.logic.service;

import com.is.servidor_barrio.business.domain.entity.CuentaCorreo;
import com.is.servidor_barrio.business.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CuentaCorreoServiceImpl extends BaseServiceImpl<CuentaCorreo, Long> implements CuentaCorreoService {
    @Autowired
    public CuentaCorreoServiceImpl(BaseRepository<CuentaCorreo, Long> baseRepository) {
        super(baseRepository);
    }

}