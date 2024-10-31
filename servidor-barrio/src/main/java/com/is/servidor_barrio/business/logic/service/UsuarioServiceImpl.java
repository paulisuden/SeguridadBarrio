package com.is.servidor_barrio.business.logic.service;

import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.entity.Usuario;
import com.is.servidor_barrio.business.repository.BaseRepository;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long> {

  public UsuarioServiceImpl(BaseRepository<Usuario, Long> baseRepository) {
    super(baseRepository);
  }

}
