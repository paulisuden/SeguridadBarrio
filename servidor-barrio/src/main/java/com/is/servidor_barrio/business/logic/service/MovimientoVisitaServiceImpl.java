package com.is.servidor_barrio.business.logic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.entity.MovimientoVisita;
import com.is.servidor_barrio.business.repository.BaseRepository;
import com.is.servidor_barrio.business.repository.MovimientoVisitaRepository;

import jakarta.transaction.Transactional;


@Service
public class MovimientoVisitaServiceImpl extends BaseServiceImpl<MovimientoVisita, String>
    implements MovimientoVisitaService {

    private final MovimientoVisitaRepository movimientoVisitaRepository;

    @Autowired
    public MovimientoVisitaServiceImpl(BaseRepository<MovimientoVisita, String> baseRepository, MovimientoVisitaRepository movimientoVisitaRepository) {
      super(baseRepository);
      this.movimientoVisitaRepository = movimientoVisitaRepository;
    }

    @Override
    @Transactional
    public List<MovimientoVisita> listarPorInmuebleId(String id) {
      List<MovimientoVisita> entities = movimientoVisitaRepository.listarPorInmuebleId(id);
        return entities;
    }
}


