package com.is.servidor_barrio.business.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.entity.Habitante;
import com.is.servidor_barrio.business.repository.BaseRepository;

@Service
public class HabitanteServiceImpl extends BaseServiceImpl<Habitante, Long> implements HabitanteService {
  @Autowired
  public HabitanteServiceImpl(BaseRepository<Habitante, Long> baseRepository) {
    super(baseRepository);
  }

}