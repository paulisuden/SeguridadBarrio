package com.is.servidor_barrio.business.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.entity.Imagen;
import com.is.servidor_barrio.business.repository.BaseRepository;

@Service
public class ImagenServiceImpl extends BaseServiceImpl<Imagen, Long>
    implements ImagenService {
  @Autowired
  public ImagenServiceImpl(BaseRepository<Imagen, Long> baseRepository) {
    super(baseRepository);
  }

}