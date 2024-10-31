package com.is.servidor_barrio.business.repository;

import com.is.servidor_barrio.business.domain.entity.Empresa;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends BaseRepository<Empresa, Long> {
}
