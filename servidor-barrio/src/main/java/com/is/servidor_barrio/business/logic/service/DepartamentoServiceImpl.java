package com.is.servidor_barrio.business.logic.service;

import com.is.servidor_barrio.business.domain.entity.Departamento;
import com.is.servidor_barrio.business.repository.BaseRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartamentoServiceImpl extends BaseServiceImpl<Departamento, String> implements DepartamentoService {

    public DepartamentoServiceImpl(BaseRepository<Departamento, String> baseRepository) {
        super(baseRepository);
    }
}
