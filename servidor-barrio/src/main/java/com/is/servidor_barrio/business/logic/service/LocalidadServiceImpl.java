package com.is.servidor_barrio.business.logic.service;

import com.is.servidor_barrio.business.domain.entity.Localidad;
import com.is.servidor_barrio.business.repository.BaseRepository;
import org.springframework.stereotype.Service;

@Service
public class LocalidadServiceImpl extends BaseServiceImpl<Localidad, String> implements LocalidadService {
    public LocalidadServiceImpl(BaseRepository<Localidad, String> baseRepository) {
        super(baseRepository);
    }
}
