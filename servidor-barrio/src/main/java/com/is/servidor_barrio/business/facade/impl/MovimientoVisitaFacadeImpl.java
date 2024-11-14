package com.is.servidor_barrio.business.facade.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.servidor_barrio.business.domain.dto.movimientoVisita.MovimientoVisitaCreateDTO;
import com.is.servidor_barrio.business.domain.dto.movimientoVisita.MovimientoVisitaDTO;
import com.is.servidor_barrio.business.domain.entity.MovimientoVisita;
import com.is.servidor_barrio.business.facade.BaseFacadeImpl;
import com.is.servidor_barrio.business.logic.service.BaseService;
import com.is.servidor_barrio.business.logic.service.VisitanteServiceImpl;
import com.is.servidor_barrio.business.mapper.BaseMapper;
import com.is.servidor_barrio.business.logic.service.InmuebleServiceImpl;
import com.is.servidor_barrio.business.logic.service.MovimientoVisitaServiceImpl;

@Service
public class MovimientoVisitaFacadeImpl extends
        BaseFacadeImpl<MovimientoVisita, MovimientoVisitaDTO, MovimientoVisitaCreateDTO, MovimientoVisitaCreateDTO, String> {

    @Autowired
    private VisitanteServiceImpl visitanteServiceImpl;
    @Autowired
    private InmuebleServiceImpl inmuebleServiceImpl;

    @Autowired
    private MovimientoVisitaServiceImpl movimientoVisitaServiceImpl;

    public MovimientoVisitaFacadeImpl(BaseService<MovimientoVisita, String> baseService,
            BaseMapper<MovimientoVisita, MovimientoVisitaDTO, MovimientoVisitaCreateDTO, MovimientoVisitaCreateDTO> baseMapper) {
        super(baseService, baseMapper);
    }

    @Override
    public MovimientoVisitaDTO save(MovimientoVisitaCreateDTO movimientoVisitaCreateDTO) throws Exception {

        var movimientoVisitaEntity = baseMapper.toEntityCreate(movimientoVisitaCreateDTO);

        var visitanteEntity = visitanteServiceImpl.findById(movimientoVisitaCreateDTO.getIdVisitante());
        var inmuebleEntity = inmuebleServiceImpl.findById(movimientoVisitaCreateDTO.getIdInmueble());
        movimientoVisitaEntity.setVisitante(visitanteEntity);
        movimientoVisitaEntity.setInmueble(inmuebleEntity);

        var entityCreated = baseService.save(movimientoVisitaEntity);
        return baseMapper.toDTO(entityCreated);
    }

    @Override
    public MovimientoVisitaDTO update(String id, MovimientoVisitaCreateDTO movimientoVisitaCreateDTO) throws Exception {
        var movimientoVisitaEntity = baseService.findById(id);
        baseMapper.toUpdate(movimientoVisitaEntity, movimientoVisitaCreateDTO);

        // VISITANTE
        if (!movimientoVisitaEntity.getVisitante().getId().equals(movimientoVisitaCreateDTO.getIdVisitante())) {
            var visitanteEntity = visitanteServiceImpl.findById(movimientoVisitaCreateDTO.getIdVisitante());
            movimientoVisitaEntity.setVisitante(visitanteEntity);
        }

        // INMUEBLE
        if (!movimientoVisitaEntity.getInmueble().getId().equals(movimientoVisitaCreateDTO.getIdInmueble())) {
            var inmuebleEntity = inmuebleServiceImpl.findById(movimientoVisitaCreateDTO.getIdInmueble());
            movimientoVisitaEntity.setInmueble(inmuebleEntity);
        }

        var updatedEntity = baseService.update(id, movimientoVisitaEntity);
        return baseMapper.toDTO(updatedEntity);
    }

    public List<MovimientoVisitaDTO> listarPorInmuebleId(String id) throws Exception {
    var entities = movimientoVisitaServiceImpl.listarPorInmuebleId(id);
    return entities
        .stream()
        .map(baseMapper::toDTO)
        .collect(Collectors.toList());
    }

}
