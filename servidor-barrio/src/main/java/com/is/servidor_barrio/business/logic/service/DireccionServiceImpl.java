package com.is.servidor_barrio.business.logic.service;

import com.is.servidor_barrio.business.domain.entity.Direccion;
import com.is.servidor_barrio.business.repository.BaseRepository;
import org.springframework.stereotype.Service;

@Service
public class DireccionServiceImpl extends BaseServiceImpl<Direccion, String> implements DireccionService {
    public DireccionServiceImpl(BaseRepository<Direccion, String> baseRepository) {
        super(baseRepository);
    }
}
