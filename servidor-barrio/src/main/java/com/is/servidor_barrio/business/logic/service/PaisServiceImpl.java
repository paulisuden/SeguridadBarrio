package com.is.servidor_barrio.business.logic.service;

import com.is.servidor_barrio.business.domain.entity.Pais;
import com.is.servidor_barrio.business.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaisServiceImpl extends BaseServiceImpl<Pais, String> implements PaisService {
    @Autowired
    public PaisServiceImpl(BaseRepository<Pais, String> baseRepository) {
        super(baseRepository);
    }
}