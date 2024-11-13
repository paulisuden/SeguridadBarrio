package com.is.servidor_barrio.business.logic.service;

import com.is.servidor_barrio.business.domain.entity.Provincia;

import java.util.Optional;

public interface ProvinciaService extends BaseService<Provincia, String> {

    Optional<Provincia> findByNameAndIdPais(String nombre, String idPais);
}
