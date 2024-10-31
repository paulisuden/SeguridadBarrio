package com.is.servidor_barrio.business.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.entity.Usuario;
import com.is.servidor_barrio.business.repository.BaseRepository;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long> implements UsuarioService {
  @Autowired
  public UsuarioServiceImpl(BaseRepository<Usuario, Long> baseRepository) {
    super(baseRepository);
  }

}
