package com.is.servidor_barrio.business.logic.service;

import com.is.servidor_barrio.business.domain.entity.Pais;
import com.is.servidor_barrio.business.domain.entity.Provincia;
import com.is.servidor_barrio.business.repository.BaseRepository;
import com.is.servidor_barrio.business.repository.ProvinciaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProvinciaServiceImpl extends BaseServiceImpl<Provincia,Long> implements ProvinciaService{

    private ProvinciaRepository provinciaRepository;

    @Autowired
    public ProvinciaServiceImpl(BaseRepository<Provincia, Long> baseRepository, ProvinciaRepository provinciaRepository) {
        super(baseRepository);
        this.provinciaRepository = provinciaRepository;
    }

    @Override
    public Optional<Provincia> findByNameAndIdPais(String nombre, Long idPais) {
        return provinciaRepository.findProvinciaByNombreAndPaisId(nombre, idPais);
    }

}
