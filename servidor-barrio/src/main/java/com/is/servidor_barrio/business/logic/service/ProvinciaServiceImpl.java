package com.is.servidor_barrio.business.logic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.entity.Provincia;
import com.is.servidor_barrio.business.repository.BaseRepository;
import com.is.servidor_barrio.business.repository.ProvinciaRepository;

@Service
public class ProvinciaServiceImpl extends BaseServiceImpl<Provincia, String> implements ProvinciaService {

    private ProvinciaRepository provinciaRepository;

    @Autowired
    public ProvinciaServiceImpl(BaseRepository<Provincia, String> baseRepository,
            ProvinciaRepository provinciaRepository,
            PaisServiceImpl baseService) {

        super(baseRepository);
        this.provinciaRepository = provinciaRepository;
    }

    @Override
    public Optional<Provincia> findByNameAndIdPais(String nombre, String idPais) {
        return provinciaRepository.findProvinciaByNombreAndPaisId(nombre, idPais);
    }

}
