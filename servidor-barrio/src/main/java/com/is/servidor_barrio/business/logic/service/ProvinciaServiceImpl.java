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

    private PaisServiceImpl baseService;

    @Autowired
    public ProvinciaServiceImpl(BaseRepository<Provincia, Long> baseRepository, ProvinciaRepository provinciaRepository, PaisServiceImpl baseService) {
        super(baseRepository);
        this.provinciaRepository = provinciaRepository;
        this.baseService = baseService;
    }

    @Override
    public Optional<Provincia> findByNameAndIdPais(String nombre, Long idPais) {
        return provinciaRepository.findProvinciaByNombreAndPaisId(nombre, idPais);
    }

    @Override
    @Transactional
    public Provincia save(Provincia entity) throws Exception {
        try {
            System.out.println("holi");
            Pais pais = baseService.findById(entity.getPais().getId());
            System.out.println(pais);
            entity.setPais(pais);
            entity = provinciaRepository.save(entity);
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
