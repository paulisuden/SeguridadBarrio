package com.is.servidor_barrio.business.logic.service;

import java.util.List;

import com.is.servidor_barrio.business.domain.entity.MovimientoVisita;

public interface MovimientoVisitaService extends BaseService<MovimientoVisita, String> {
    public List<MovimientoVisita> listarPorInmuebleId(String id);
}
