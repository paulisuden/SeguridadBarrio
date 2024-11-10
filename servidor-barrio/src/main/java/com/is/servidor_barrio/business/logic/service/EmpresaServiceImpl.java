package com.is.servidor_barrio.business.logic.service;

import com.is.servidor_barrio.business.domain.entity.Empresa;
import com.is.servidor_barrio.business.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaServiceImpl extends BaseServiceImpl<Empresa,Long> implements EmpresaService {

    @Autowired
    public EmpresaServiceImpl(BaseRepository<Empresa, Long> baseRepository) {
        super(baseRepository);
    }
}